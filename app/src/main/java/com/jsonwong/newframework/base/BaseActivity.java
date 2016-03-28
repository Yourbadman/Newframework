package com.jsonwong.newframework.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jsonwong.newframework.AppContext;
import com.jsonwong.newframework.AppManager;
import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.swipeback.SwipeBackActivity;
import com.jsonwong.newframework.base.swipeback.SwipeBackLayout;
import com.jsonwong.newframework.fragment.NewsDetailFragment_;
import com.jsonwong.newframework.interf.BaseViewInterface;
import com.jsonwong.newframework.ui.DetailActivity;
import com.jsonwong.newframework.ui.dialog.CommonToast;
import com.jsonwong.newframework.ui.dialog.DialogControl;
import com.jsonwong.newframework.util.DialogHelp;
import com.jsonwong.newframework.util.StringUtils;
import com.jsonwong.newframework.util.SwipebackUtils;
import com.jsonwong.newframework.util.TDevice;


import butterknife.ButterKnife;

/**
 * baseActionBar Activity
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:30:15 引用自：tonlin
 */
public abstract class BaseActivity extends SwipeBackActivity implements
        DialogControl, View.OnClickListener, BaseViewInterface {
    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";

    private boolean _isVisible;
    private ProgressDialog _waitDialog;

    protected LayoutInflater mInflater;
    protected ActionBar mActionBar;
    private TextView mTvActionTitle;


    //返回的布局
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TDevice.hideSoftKeyboard(getCurrentFocus());
        ButterKnife.reset(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppContext.getNightModeSwitch()) {
            setTheme(R.style.AppBaseTheme_Night);
        } else {
            setTheme(R.style.AppBaseTheme_Light);
        }

        if (hasAddActivity2Manage())
            AppManager.getAppManager().addActivity(this);

        if (!hasActionBar()) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        } else {
            initActionBar(mActionBar);
        }
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //设置可以滑动返回的大小
        mSwipeBackLayout.setEdgeDp(200);
        // 通过注解绑定控件
        ButterKnife.inject(this);

        initView(savedInstanceState);
        initData();
        _isVisible = true;
    }

    @Override
    public void initView(Bundle bundle) {
        BaseFragment fragment = getShowFragment();
        if (fragment == null)
            return;
        setActionBarTitle(getActionBarTitle());

        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        Bundle arguments = getFragmentArguments();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }


        trans.replace(R.id.option, fragment);
        trans.commitAllowingStateLoss();

    }

    protected void onBeforeSetContentLayout() {
    }

    protected boolean hasAddActivity2Manage() {
        return true;
    }

    protected boolean hasActionBar() {
        return true;
    }

    protected int getLayoutId() {
        return R.layout.base_fragment_content;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

        protected boolean hasBackButton() {
        return false;
    }

    protected abstract BaseFragment getShowFragment();


    protected abstract Bundle getFragmentArguments();


    protected void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;
        if (hasBackButton()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    public void setActionBarTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }
        if (hasActionBar() && mActionBar != null) {
            if (mTvActionTitle != null) {
                mTvActionTitle.setText(title);
            }
            mActionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showToast(int msgResid, int icon, int gravity) {
        showToast(getString(msgResid), icon, gravity);
    }

    public void showToast(String message, int icon, int gravity) {
        CommonToast toast = new CommonToast(this);
        toast.setMessage(message);
        toast.setMessageIc(icon);
        toast.setLayoutGravity(gravity);
        toast.show();
    }

    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelp.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        // setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }
}
