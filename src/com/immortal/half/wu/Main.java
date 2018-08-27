package com.immortal.half.wu;


import java.sql.*;


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
        nativeSqlTest();

    }

    private static void nativeSqlTest() {
        Connection connection = null;
        SqlUtl sqlUtl = new SqlUtl();
//        try {

        String sqlUrl = "jdbc:mysql://localhost:3306/java_sql?useSSL=false&serverTimezone=GMT%2B8";
        String sqlUser = "root";
        String sqlPassWord = "mysql2b";

        log("连接数据库");
        try {
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassWord);

            sqlUtl.queryAllUser(connection);
            sqlUtl.addUserInfo(connection, 11, "newUserName", "newPassWord");
            sqlUtl.queryAllUser(connection);
            sqlUtl.updataUser(connection, 11, "changeUserName", "changePassWord");
            sqlUtl.queryAllUser(connection);
            sqlUtl.removeUserInfo(connection, 11, "changeUserName", "changePassWord");
            sqlUtl.queryAllUser(connection);

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
