package com.jsonwong.newframework.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jsonwong.newframework.R;
import com.jsonwong.newframework.base.BaseActivity;
import com.jsonwong.newframework.base.BaseFragment;
import com.jsonwong.newframework.fragment.NewsDetailFragment;
import com.jsonwong.newframework.fragment.NewsDetailFragment_;


/**
 * 详情activity（包括：资讯）
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年10月11日 上午11:18:41
 */
public class DetailActivity extends BaseActivity {

    public static final int DISPLAY_NEWS = 0;
    public static final String NEWS_ID = "news_id_1";
    public static final String NEWS_IMAGE_URL = "news_image_url";
    public static final String BUNDLE_KEY_DISPLAY_TYPE = "BUNDLE_KEY_DISPLAY_TYPE";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.actionbar_title_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int displayType = getIntent().getIntExtra(BUNDLE_KEY_DISPLAY_TYPE,
                DISPLAY_NEWS);
        BaseFragment fragment = null;
        int actionBarTitle = 0;
        switch (displayType) {
            case DISPLAY_NEWS:
                actionBarTitle = R.string.actionbar_title_news;
                fragment = new NewsDetailFragment_();
                break;

            default:
                break;
        }
        setActionBarTitle(actionBarTitle);

        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();

        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = new Bundle();
            bundle.putString(DetailActivity.NEWS_ID, getIntent().getExtras().getString(DetailActivity.NEWS_ID));
            bundle.putString(DetailActivity.NEWS_IMAGE_URL, getIntent().getExtras().getString(DetailActivity.NEWS_IMAGE_URL));
            fragment.setArguments(bundle);
        }

        trans.replace(R.id.container, fragment);
        trans.commitAllowingStateLoss();
        /*if (fragment instanceof OnSendClickListener) {
            currentFragment = (OnSendClickListener) fragment;
        } else {
            currentFragment = new OnSendClickListener() {
                @Override
                public void onClickSendButton(Editable str) {
                }

                @Override
                public void onClickFlagButton() {
                }
            };
        }*/
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void initView() {
      /*  if (currentFragment instanceof TweetDetailFragment
                || currentFragment instanceof TeamTweetDetailFragment
                || currentFragment instanceof TeamDiaryDetailFragment
                || currentFragment instanceof TeamIssueDetailFragment
                || currentFragment instanceof TeamDiscussDetailFragment
                || currentFragment instanceof CommentFrament) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.emoji_keyboard, emojiFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.emoji_keyboard, toolFragment).commit();
        }
        toolFragment.setOnActionClickListener(new OnActionClickListener() {
            @Override
            public void onActionClick(ToolAction action) {
                switch (action) {
                    case ACTION_CHANGE:
                    case ACTION_WRITE_COMMENT:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.footer_menu_slide_in,
                                        R.anim.footer_menu_slide_out)
                                .replace(R.id.emoji_keyboard, emojiFragment)
                                .commit();
                        break;
                    case ACTION_FAVORITE:
                        ((CommonDetailFragment) currentFragment)
                                .handleFavoriteOrNot();
                        break;
                    case ACTION_REPORT:
                        ((CommonDetailFragment) currentFragment).onReportMenuClick();
                        break;
                    case ACTION_SHARE:
                        ((CommonDetailFragment) currentFragment).handleShare();
                        break;
                    case ACTION_VIEW_COMMENT:
                        ((CommonDetailFragment) currentFragment)
                                .onCilckShowComment();
                        break;
                    default:
                        break;
                }
            }
        });

    }*/

       /* @Override
        public void initData () {
        }

    @Override
    public void onClickSendButton(Editable str) {
        currentFragment.onClickSendButton(str);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                if (emojiFragment.isShowEmojiKeyBoard()) {
                    emojiFragment.hideAllKeyBoard();
                    return true;
                }
                if (emojiFragment.getEditText().getTag() != null) {
                    emojiFragment.getEditText().setTag(null);
                    emojiFragment.getEditText().setHint("说点什么吧");
                    return true;
                }
            } catch (NullPointerException e) {
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setCommentCount(int count) {
        try {
            toolFragment.setCommentCount(count);
        } catch (Exception e) {
        }
    }

    @Override
    public void onClickFlagButton() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.footer_menu_slide_in,
                        R.anim.footer_menu_slide_out)
                .replace(R.id.emoji_keyboard, toolFragment).commit();
        try {

        } catch (Exception e) {
        }*/
    }

    @Override
    public void initData() {

    }
}
