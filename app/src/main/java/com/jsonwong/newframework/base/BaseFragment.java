package com.jsonwong.newframework.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.interf.BaseFragmentInterface;
import com.squareup.leakcanary.RefWatcher;


/**
 * 碎片基类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:18:46
 */
public class BaseFragment extends Fragment implements
        View.OnClickListener, BaseFragmentInterface {
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;    // 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;

    protected LayoutInflater mInflater;

    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        afterCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void hideWaitDialog() {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            ((DialogControl) activity).hideWaitDialog();
//        }
    }

    protected ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    protected ProgressDialog showWaitDialog(int resid) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(resid);
//        }
        return null;
    }

    protected ProgressDialog showWaitDialog(String str) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(str);
//        }
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = AppContext.getRefWatcher();
        if (refWatcher != null)
            refWatcher.watch(this);
    }
}
