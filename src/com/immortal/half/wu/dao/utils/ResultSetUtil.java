package com.immortal.half.wu.dao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库结果集转Bean工具类
 */
public class ResultSetUtil {

    /**
     * 结果集转bean
     * @param calss bean class
     * @param resultSet 结果集
     * @param <T> bean
     * @return bean实例
     */
    public static <T> T getBean(Class<T> calss, ResultSet resultSet) {
        // TODO Auto-generated method stub
        // 创建对象
        T object = null;
        try {
            object = createBean(calss, resultSet);

        } catch (Exception e) {
            System.out.println("执行CreateBeanImp类getBean方法出现异常");
            System.out.println("=================异常报告=================");
            e.printStackTrace();
            System.out.println("=================end=================");
        }

        return object;
    }

    /**
     * 结果集转Bean列表
     * @param calss bean class
     * @param resultSet 结果集
     * @param <T> bean
     * @return bean 列表
     */
    public static <T> List<T> getBeans(Class<T> calss, ResultSet resultSet) {
        List<T> ts = null;
        try {
            ts = new ArrayList<>();
            int i = 0;
            while (resultSet.next()) {
                ts.add(createBean(calss, resultSet));
            }
        } catch (Exception e) {
            System.out.println("执行CreateBeanImp类getBean方法出现异常");
            System.out.println("=================异常报告=================");
            e.printStackTrace();
            System.out.println("=================end=================");
        }
        return ts;
    }


    /**
     * @param calss bean class
     * @param resultSet 结果集
     * @return 实体对象
     * @throws Exception <p>
     *                   创建对象时错误,69行<br>
     *                   获取resultSet值错误81行
     *                   </p>
     */
    private static <T> T createBean(Class<T> calss, ResultSet resultSet) throws Exception {
        T object = calss.newInstance();
        // 获取字段
        Field[] fields = calss.getDeclaredFields();

        // 遍历fields
        for (Field field : fields) {
            // 获取字段名
            String fieldName = field.getName();
            if (!fieldName.equalsIgnoreCase("serialVersionUID")) {
                // 获取方法名
                String setMethodName = "set" + (char) (fieldName.charAt(0) - 32) + fieldName.substring(1);
                // 获取field类型
                Class type = field.getType();

                Method method = calss.getDeclaredMethod(setMethodName, type);
                Object fieldVlaue = resultSet.getObject(fieldName);
                System.out.println(fieldName + ":" + fieldVlaue);
                method.invoke(object, fieldVlaue);
            }

        }
        return object;
    }


    /**
     * 结果集转指定bean的json低内存
     * @param calss bean
     * @param resultSet 结果集
     * @return json数据
     */
    static JSONArray getBeansJsonArray(Class<?> calss, ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        try {
            Field[] fields = calss.getDeclaredFields();

            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                // 遍历fields
                for (Field field : fields) {
                    // 获取字段名
                    String fieldName = field.getName();
                    if (!fieldName.equalsIgnoreCase("serialVersionUID")) {
                        Object fieldVlaue = resultSet.getObject(fieldName);
                        jsonObject.put(fieldName, fieldVlaue);
//                            System.out.println(fieldName+"  "+fieldVlaue);
                    }
                }
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {
            System.out.println("执行CreateBeanImp类getBean方法出现异常");
            System.out.println("=================异常报告=================");
            e.printStackTrace();
            System.out.println("=================end=================");
        }
//            System.out.println(jsonArray);
        return jsonArray;
    }


}
