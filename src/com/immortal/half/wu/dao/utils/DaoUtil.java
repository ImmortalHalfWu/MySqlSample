package com.immortal.half.wu.dao.utils;

import com.google.gson.*;
import com.immortal.half.wu.bean.SqlQueryBean;
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DaoUtil {

    public static Map<String, Object> object2Map(@NotNull Object object) {

        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return aClass.isEnum();
            }
        }).create();
        JsonElement jsonElement = gson.toJsonTree(object);
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : entries) {
            map.put(entry.getKey(), entry.getValue().getAsString());
        }


//        PropertyFilter propertyFilter = (o, s, o1) -> o1 != null && !o1.getClass().isEnum();
//        String objectJson = JSON.toJSONString(object, propertyFilter);
//
//        JSONObject jsonObject =  (JSONObject) JSON.parse(objectJson);
//        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();

//        Map<String, Object> map = new HashMap<>();
//        for (Map.Entry<String, Object> entry : entrySet) {
//            map.put(entry.getKey(), entry.getValue());
//        }

        return map;

    }

    /**
     * Object 封装为SQLbean
     * @param tableName 表名
     * @param object boject
     * @return
     */
    public static SqlQueryBean object2SqlBean(@NotNull String tableName, @NotNull Object object) {

        Map<String, Object> stringStringMap = object2Map(object);

        return new SqlQueryBean(object.getClass(), tableName, stringStringMap);

    }
}
