package com.jsonwong.newframework.adapter;

import android.os.Bundle;

public final class ViewPageInfo {

    public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;
    public final int position;

    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args, int _position) {
        title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
        position = _position;
    }
}