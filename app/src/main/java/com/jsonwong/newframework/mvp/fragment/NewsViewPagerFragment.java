
package com.jsonwong.newframework.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jsonwong.greendao.ChannelItem;
import com.jsonwong.modle.base.ViewPageInfo;
import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.adapter.ViewPageFragmentAdapter;
import com.jsonwong.newframework.interf.OnTabReselectListener;
import com.jsonwong.newframework.mvp.delegate.ViewPaperDelegate;

import java.util.ArrayList;
import java.util.List;

import news.jsonwong.com.mvpframework.presenter.FragmentPresenter;

/**
 * 新闻viewpager页面
 *
 * @author jsonwong (http://www.jsonwong.cn)
 *         create at 2016/4/16 23:44
 */
public class NewsViewPagerFragment extends FragmentPresenter<ViewPaperDelegate> implements
        OnTabReselectListener {
    protected ViewPageFragmentAdapter mTabsAdapter;


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                viewDelegate.mTabStrip, viewDelegate.mViewPager);
        onSetupTabAdapter(mTabsAdapter, null);
    }


    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        //   bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;


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
    public void onTabReselect() {
        try {
            int currentIndex = viewDelegate.mViewPager.getCurrentItem();
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


    @Override
    protected Class<ViewPaperDelegate> getDelegateClass() {
        return ViewPaperDelegate.class;
    }

    @Override
    public void onStart() {
        super.onStart();


    }


}
