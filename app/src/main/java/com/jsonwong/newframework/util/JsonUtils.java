package com.jsonwong.newframework.util;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

/**
 * Authors：Administrator on 2016/3/28 16:02
 */
public class JsonUtils<T> {


    public JsonUtils() {

    }

    /**
     * json 2 Object
     *
     * @param clazz
     * @param json
     * @return
     */
    public T json2Object(Class<T> clazz, String json) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<T> jsonAdapter = moshi.adapter(clazz);

        try {
            return jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     * json 2 Object List
     *
     * @param clazz
     * @param json
     * @return
     */
    public List<T> json2Object(Class<T> clazz, String json) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<T> jsonAdapter = moshi.adapter(clazz);

        try {
            return jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }



}
