package news.jsonwong.com.mvpframework.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import news.jsonwong.com.mvpframework.presenter.ActivityPresenter;
import news.jsonwong.com.mvpframework.view.IDelegate;


/**
 * Activity基类
 *
 * @author kymjs (http://www.kymjs.com/) on 11/19/15.
 */
public abstract class BaseFrameActivity<T extends IDelegate> extends ActivityPresenter<T> {
    protected LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        super.onCreate(savedInstanceState);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
        super.setContentView(rootView);
    }

    @Override
    protected void initToolbar() {
//        Toolbar toolbar = viewDelegate.getToolbar();
//        if (toolbar == null) {
//            toolbar = (Toolbar) View.inflate(this, R.layout.base_toolbar, null);
//            rootView.addView(toolbar, 0);
//        }
//        if (toolbar != null) {
//
//            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//            setSupportActionBar(toolbar);
//        }


    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
