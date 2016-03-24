package com.jsonwong.newframework.bean;

import com.jsonwong.newframework.base.BaseModle;

import java.io.Serializable;
import java.util.List;

public interface ListEntity<T extends BaseModle> extends Serializable {

    public List<T> getList();
}
