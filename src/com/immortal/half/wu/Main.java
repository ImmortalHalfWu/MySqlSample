package com.immortal.half.wu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.immortal.half.wu.bean.ScanInfoBean;
import com.immortal.half.wu.bean.TestUserBean;
import com.immortal.half.wu.bean.enums.ORDER_TYPE;
import com.immortal.half.wu.bean.utils.BeanUtil;
import com.immortal.half.wu.dao.utils.SqlUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Main {


    static {
        try {
            String driverClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ScanInfoBean scanInfoBean = new ScanInfoBean(1,1,null,"tag", ORDER_TYPE.ORDER_TYPE_ALL, 1, false);
        PropertyFilter propertyFilter = (o, s, o1) -> {
//            System.out.println("o = " + o.getClass().getName() + "___o1 = " + o1.getClass().getName());
            return o1 != null && !o1.getClass().isEnum();
        };
        String s = JSON.toJSONString(scanInfoBean, propertyFilter);
        System.out.println(s);

        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
        System.out.println(entrySet);
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : entrySet) {
//            System.out.println("k = " + entry.getKey() + "___v = " + entry.getValue() + "___type" + entry.getValue().getClass().getSimpleName());
            map.put(entry.getKey(), entry.getValue());
        }
        System.out.println(map.keySet());
        System.out.println(map.values());

//        SqlUtil.insertObjectToSQL(null, new SqlQueryBean("login_user" , map));
        /*
            bean to sql
            bean --> json --> map --> keySet valueSet -->
            "insert into 表名 (keySet)"
                                + "values (?,?,? keyset 长度)"
            for (valueSet) {
                if value is Integer
                   statement.setInt(index, value);
                if value is String
                   statement.setString(index, value);
                if value is Boolean
                   statement.setBoolean(index, value);
                   ........
            }
         */

        nativeSqlTest();
    }


    private static void nativeSqlTest() {
        Connection connection = null;
        SqlUtl sqlUtl = new SqlUtl();

        String sqlUrl = "jdbc:mysql://localhost:3306/java_sql?useSSL=false&serverTimezone=GMT%2B8";
        String sqlUser = "root";
        String sqlPassWord = "mysql2b";

        log("连接数据库");
        try {
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassWord);

//            SqlUtil.selectObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null,null,null)));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
//            SqlUtil.insertObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null, "nullIDnewUserName", "nullIDnewPassWord")));
            SqlUtil.selectObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null,null,null)));
            SqlUtil.updataObjectToSQL(
                    connection,
                    BeanUtil.object2SqlBean("login_user", new TestUserBean(null,"updataName","updataPass")),
                    BeanUtil.object2SqlBean("login_user", new TestUserBean(21,"nullIDnewUserName","nullIDnewPassWord"))
            );
//            SqlUtil.delObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null,null,null)));
            SqlUtil.selectObjectToSQL(connection, BeanUtil.object2SqlBean("login_user", new TestUserBean(null,null,null)));

//            sqlUtl.queryAllUser(connection);
//            sqlUtl.updataUser(connection, 14, "changeUserName", "changePassWord");
//            sqlUtl.queryAllUser(connection);
//            sqlUtl.removeUserInfo(connection, 14, "changeUserName", "changePassWord");
//            sqlUtl.queryAllUser(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Hello MySql!");
    }


    private static void log(String text) {
        System.out.println(text);
    }


}
