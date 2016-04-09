package com.jsonwong.newframework.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
//    public T json2Object(Class<T> clazz, String json) {
//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<T> jsonAdapter = moshi.adapter(clazz);
//
//        try {
//            return jsonAdapter.fromJson(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }


    /**
     * json 2 Object List
     *
     * @param json
     * @return
     */
    public <T> List<T> json2ObjectList(String json, Class<T> clazz, String mapId) {
//        Moshi moshi = new Moshi.Builder().build();
//        //Map<String,ArrayList<T>>
//        Type listOfCardsType = Types.newParameterizedType(Map.class, String.class, List.class, clazz);
//        JsonAdapter<Map<String, ArrayList<T>>> jsonAdapter = moshi.adapter(listOfCardsType);
//
//        try {
//            return jsonAdapter.fromJson(json).get(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
        //  Gson gson = new Gson();

//        Map<String, List<T>> listMap = gson.fromJson(json, new TypeToken<Map<String, ArrayList<T>>>() {
//        }.getType());

        Gson gson = new Gson();
        List<T> lst = new ArrayList<T>();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();

        JsonArray array = object.getAsJsonArray(mapId);
        for (final JsonElement elem : array) {
            lst.add(gson.fromJson(elem, clazz));
        }

        //移除第一个广告新闻
        lst.remove(0);
        return lst;
    }


}
