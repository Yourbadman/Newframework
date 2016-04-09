
package com.jsonwong.newframework.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.json.greendao.ChannelItem;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.adapter.ViewPageFragmentAdapter;
import com.jsonwong.newframework.adapter.ViewPageInfo;
import com.jsonwong.newframework.base.BaseViewPagerFragment;
import com.jsonwong.newframework.fragment.NewsListFragment;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 新闻viewpager页面
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午2:21:52
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment implements
        OnTabReselectListener {

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter, Object userList) {
        if (adapter == null)
            return;
        List<ChannelItem> list;
        if (userList == null) {
            list = AppContext.getChannleItemList(getContext());
        } else {
            list = (ArrayList<ChannelItem>) userList;
        }

        ArrayList<ViewPageInfo> currentList = adapter.getTabs();
        int showPosition = 0;
        //初始化Tabs
        if (currentList.size() == 0) {
            for (int i = 0; i < list.size(); i++) {
                adapter.addTab(list.get(i).getName(), list.get(i).getChannelId() + "", NewsListFragment.class,
                        getBundle(list.get(i)), i);
            }
        } else {


            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {

                    int needRemove = currentList.size() - list.size();
                    for (int k = 0; k < needRemove; k++) {
                        adapter.remove(list.size() + k);

                    }
                }

                //当前位置item信息一致，无须变动
                if (i < currentList.size() && currentList.get(i).tag.equals(list.get(i).getChannelId()))
                    continue;

                /**
                 * 当前位置的item信息不一致，需要插入，插入前查找currentTab是否存在item，则复用item对应的view，否则就inflate一个新的view
                 */
                if (i < currentList.size() && !currentList.get(i).tag.equals(list.get(i).getChannelId())) {
                    boolean isAdd = false;
                    for (int j = i + 1; j < currentList.size(); j++) {
                        //存在则取Fragment的view,后续无需inflate
                        if (currentList.get(j).tag.equals(list.get(i).getChannelId())) {
                            isAdd = true;

                            adapter.addTab(list.get(i).getName(), list.get(i).getChannelId() + "", NewsListFragment.class,
                                    getBundle(list.get(i)), i, adapter.getItem(j).getView());
                            showPosition = i;
                            adapter.remove(j + 1);
                            break;
                        }
                    }
                    //当currentTabs不存在需要添加的tab，直接添加（需要inflate）
                    if (!isAdd) {
                        showPosition = i;
                        adapter.addTab(list.get(i).getName(), list.get(i).getChannelId() + "", NewsListFragment.class,
                                getBundle(i), i);
                    }
                }

                //当新的tabs大于currentTabs ，直接添加
                if (i > currentList.size() - 1) {
                    showPosition = i;
                    adapter.addTab(list.get(i).getName(), list.get(i).getChannelId() + "", NewsListFragment.class,
                            getBundle(i), i);
                }


            }
            // adapter.notifyDataSetChanged();

            //Tabs有变化时

//            WeakHashMap<String, View> cacheFragment = new WeakHashMap<String, View>();
//            //比对currentTabs和改动后的tabs
//            Weak
//            for (int i = 0; i < currentList.size(); i++) {
//                //当前位置item信息一致，无须变动
//                if (i < list.size() && currentList.get(i).tag.equals(list.get(i).getChannelId()))
//                    continue;
//                if (i < list.size() && !currentList.get(i).tag.equals(list.get(i).getChannelId())) {
//                    hasAdd.add(list.get(i).getChannelId());
//
//                    //存入tabs当前位置的view，做缓存
//                    cacheFragment.put(currentList.get(i).tag, adapter.getItem(i).getView());
//
//                    for (int j = i; j < list.size(); j++) {
//
//                    }
//
//
//                    adapter.addTab(list.get(i).getName(), list.get(i).getChannelId() + "", NewsListFragment.class,
//                            getBundle(1), i);
//                }
//                //需要删除重复Item
//                if (i < currentList.size() && hasAdd.contains(currentList.get(i).tag)) {
//                    adapter.remove(i);
//                }
//                currentList.remove(i);
//
//            }
            /*for (int i = 0; i < currentList.size(); i++) {
                adapter.remove(i + list.size());
            }*/

        }

        //设置主显示，当添加新的Fragment是显示第一个新添加
        adapter.setPrimaryItem(null, showPosition, null);
        adapter.getmPagerStrip().scrollToChild(showPosition, 0);
        adapter.getmPagerStrip().selectedTab(showPosition);
        adapter.notifyDataSetChanged();


    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        //   bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }

    @Override
    protected void setScreenPageLimit() {
        //mViewPager.setOffscreenPageLimit(3);
    }

    /**
     * 基类会根据不同的channlItem展示相应的数据
     *
     * @return
     */
    private Bundle getBundle(ChannelItem channelItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NewsListFragment.BUNDLE_CHANNLE_ID, channelItem);
        return bundle;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onTabReselect() {
        try {
            int currentIndex = mViewPager.getCurrentItem();
            Fragment currentFragment = getChildFragmentManager().getFragments()
                    .get(currentIndex);
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
            }
        } catch (NullPointerException e) {
        }
    }


    /**
     * 以下Rxjava测试RxBus
     */

    private RxBus _rxBus;
    private CompositeSubscription _subscriptions;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _rxBus = AppContext.getInstance().getRxBusSingleton();
        _rxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object dataI) {

                onSetupTabAdapter(mTabsAdapter, dataI);
            }
        });
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        _subscriptions.clear();
//    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Demo", "onCreate");


    }

    @Override
    public void onStart() {
        super.onStart();


    }


}
