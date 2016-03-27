package com.jsonwong.newframework.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.greendao.ChannelItem;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.adapter.DragAdapter;
import com.jsonwong.newframework.adapter.OtherAdapter;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.db.service.ChannelManageService;
import com.jsonwong.newframework.rxbus.RxBus;
import com.jsonwong.newframework.util.Constants;
import com.jsonwong.newframework.widget.DragGrid;
import com.jsonwong.newframework.widget.OtherGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by laucherish on 16/3/17.
 */
public class ChannelFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private Context context;
    /**
     * 用户栏目的GRIDVIEW
     */
    @InjectView(R.id.userGridView)
    public DragGrid userGridView;
    /**
     * 其它栏目的GRIDVIEW
     */

    @InjectView(R.id.otherGridView)
    public OtherGridView otherGridView;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    /**
     * 用户栏目对应的适配器，可以拖动
     */

    DragAdapter userAdapter;
    /**
     * 其它栏目对应的适配器
     */

    OtherAdapter otherAdapter;
    /**
     * 其它栏目列表
     */
    List<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
    /**
     * 用户栏目列表
     */
    List<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /**
     * 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。
     */
    boolean isMove = false;


    private RxBus _rxBus;

    @Override
    protected int getLayoutId() {
        return R.layout.channel;
    }


    @Override
    public void initView(View view) {
        context = getContext();
        _rxBus = AppContext.getInstance().getRxBusSingleton();
    }


    @Override
    public void initData() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//         actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);

        // mToolbar.setNavigationIcon(R.drawable.actionbar_search_icon);


        userChannelList = ChannelManageService.getInstance(context).getUserChannel();
        otherChannelList = ChannelManageService.getInstance(context).getOtherChannel();

        userAdapter = new DragAdapter(context, userChannelList);
        userGridView.setAdapter(userAdapter);
        otherAdapter = new OtherAdapter(context, otherChannelList);
        otherGridView.setAdapter(otherAdapter);
        // 设置GRIDVIEW的ITEM的点击监听
        otherGridView.setOnItemClickListener(this);
        userGridView.setOnItemClickListener(this);
    }

    /**
     * GRIDVIEW对应的ITEM点击监听接口
     */
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        // 如果点击的时候，之前动画还没结束，那么就让点击事件无效
        if (isMove) {
            return;
        }
        switch (parent.getId()) {
            case R.id.userGridView:
                // position为 0，1 的不可以进行任何操作
                if (position != 0 && position != 1) {
                    final ImageView moveImageView = getView(view);
                    if (moveImageView != null) {
                        TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                        final int[] startLocation = new int[2];
                        newTextView.getLocationInWindow(startLocation);
                        final ChannelItem channel = ((DragAdapter) parent.getAdapter())
                                .getItem(position);// 获取点击的频道内容
                        otherAdapter.setVisible(false);
                        // 添加到最后一个
                        otherAdapter.addItem(channel);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int[] endLocation = new int[2];
                                    // 获取终点的坐标
                                    otherGridView
                                            .getChildAt(otherGridView.getLastVisiblePosition())
                                            .getLocationInWindow(endLocation);
                                    moveAnim(moveImageView, startLocation, endLocation, channel,
                                            userGridView);
                                    userAdapter.setRemove(position);
                                    ChannelManageService.getInstance(context).updateChannel(channel, Constants.CHANNEL_NOT_SELETED);

                                } catch (Exception localException) {
                                }
                            }
                        }, 50L);
                    }
                }
                break;
            case R.id.otherGridView:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((OtherAdapter) parent.getAdapter())
                            .getItem(position);
                    userAdapter.setVisible(false);
                    // 添加到最后一个
                    userAdapter.addItem(channel);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int[] endLocation = new int[2];
                                // 获取终点的坐标
                                userGridView.getChildAt(userGridView.getLastVisiblePosition())
                                        .getLocationInWindow(endLocation);
                                moveAnim(moveImageView, startLocation, endLocation, channel,
                                        otherGridView);
                                otherAdapter.setRemove(position);
                                ChannelManageService.getInstance(context).updateChannel(channel, Constants.CHANNEL_SELETED);

                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void moveAnim(View moveView, int[] startLocation, int[] endLocation,
                          final ChannelItem moveChannel,
                          final GridView clickGridView) {
        // 将当前栏目增加到改变过的listview中 若栏目已经存在删除点，不存在添加进去

        int[] initLocation = new int[2];
        // 获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        // 得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        // 创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);// 动画时间
        // 动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);// 动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    otherAdapter.setVisible(true);
                    otherAdapter.notifyDataSetChanged();
                    userAdapter.remove();
                } else {
                    userAdapter.setVisible(true);
                    userAdapter.notifyDataSetChanged();
                    otherAdapter.remove();
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     *
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(context);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /**
     * 获取点击的Item的对应View，
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(context);
        iv.setImageBitmap(cache);
        return iv;
    }

    /**
     * 退出时候保存选择后数据库的设置
     */
    List<ChannelItem> saveChannel() {

        ChannelManageService.getInstance(context).deleteAllChannel();
        List<ChannelItem> userList = userAdapter.getChannnelLst();
        //排序
        for (int i = 0; i < userList.size(); i++) {

            userList.get(i).setOrderId(i + 1);
        }
        ChannelManageService.getInstance(context).saveUserChannel(userList);
        List<ChannelItem> otherList = otherAdapter.getChannnelLst();
        for (int i = 0; i < otherList.size(); i++) {

            otherList.get(i).setOrderId(userList.size() + 1 + i);
        }
        ChannelManageService.getInstance(context).saveOtherChannel(otherList);
        return userList;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (userAdapter.isListChanged()) {
            // MainActivity.isChange = true;

            if (_rxBus.hasObservers()) {
                _rxBus.send(saveChannel());
            }
        }
    }


}
