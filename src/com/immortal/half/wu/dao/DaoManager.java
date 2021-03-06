package com.immortal.half.wu.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.immortal.half.wu.bean.*;
import com.immortal.half.wu.dao.impls.SQLDaoImpl;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoManager {

    static {
        try {
            String driverClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static DaoManager daoManager;
    private SQLDaoImpl sqlDaoImpl;
    private Connection connection;

    private DaoManager() throws SQLException {
        connectSQL();
        sqlDaoImpl = SQLDaoImpl.getInstance();
    }

    public static void init() throws SQLException {
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                daoManager = new DaoManager();
            }
        }
    }

    public static @Nullable DaoManager instance() {
        return daoManager;
    }

    public <T> List<T> selectSQLForBean(T bean) {
        ArrayList<T> objects = new ArrayList<>();
        try {
            if (connectSQL()) {

                JSONArray jsonArray = sqlDaoImpl.selectFromSQL(
                        connection,
                        findTableNameByBean(bean),
                        bean);

                if (jsonArray.isEmpty()) {
                    objects.trimToSize();
                    return objects;
                }

                for (int i = 0, size = jsonArray.size(); i < size; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    objects.add(JSON.parseObject(jsonObject.toJSONString(),  (Class<T>) bean.getClass()));
                }
                objects.trimToSize();
                return objects;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        objects.trimToSize();
        return objects;
    }

    public boolean insertBeanToSQL(Object bean) {
        try {
            sqlDaoImpl.addToSQL(connection, findTableNameByBean(bean), bean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBeanForSQL(Object bean) {
        try {
            sqlDaoImpl.deleteFromSQL(connection, findTableNameByBean(bean), bean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updataBeanForSQL(Object newBean, Object oldBean) {
        try {
            sqlDaoImpl.updataToSQL(connection, findTableNameByBean(oldBean), newBean, oldBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean connectSQL() {
        try {
            if (connection == null || connection.isClosed()) {
                String sqlUrl = "jdbc:mysql://localhost:3306/zhitou?useSSL=false&serverTimezone=GMT%2B8&autoReconnect=true";
                String sqlUser = "root";
                String sqlPassWord = "mysql2b";
                System.out.println("尝试连接数据库");
                connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassWord);
                System.out.println("连接数据库成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
            return false;
        }
        return true;
    }

    private static @Nullable String findTableNameByBean(Object bean) {

        Class<?> aClass = bean.getClass();
        if (aClass.equals(UserInfoBean.class)) {
            return "user_info";
        }
        if (aClass.equals(UserVipInfoBean.class)) {
            return "vip_info";
        }
        if (aClass.equals(DocumentaryInfoBean.class)) {
            return "documentary_info";
        }
        if (aClass.equals(HistoricalRecordsBean.class)) {
            return "historical_records";
        }
        if (aClass.equals(ScanInfoBean.class)) {
            return "scan_info";
        }
        if (aClass.equals(TestUserBean.class)) {
            return "login_user";
        }
        if (aClass.equals(PayQRcodeBean.class)) {
            return "qrcode";
        }
        if (aClass.equals(PayInfoBean.class)) {
            return "pay_info";
        }

        return null;

    }

    public void release() {
        sqlDaoImpl = null;
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
