package com.jsonwong.modle;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public interface ListEntity<T extends Parcelable> extends Serializable {

    public List<T> getList();
}
