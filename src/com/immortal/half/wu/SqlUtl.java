package com.immortal.half.wu;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.sql.*;

public class SqlUtl {

     private void sqlClose(@Nullable Connection connection, @Nullable Statement statement, @Nullable ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 添加用户数据
     *
     * @param connection
     */
     boolean addUserInfo(@NotNull Connection connection, int id, String userName, String password) {

        log("添加一条新的数据");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {

            try {
                statement = connection.prepareStatement(
                        "insert into login_user (id, name, password)"
                                + "values (?,?,?)"
                );
                statement.setInt(1, id);
                statement.setString(2, userName);
                statement.setString(3, password);
                statement.executeUpdate();
                log("数据添加成功");
            } catch (SQLException e) {
                e.printStackTrace();
                log("数据添加失败");
                return false;
            } finally {
                sqlClose(null, statement, resultSet);
            }

        }
        return true;
    }

    /**
     * 移除指定用户数据
     *
     * @param connection
     * @param id
     * @param name
     * @param password
     */
    boolean removeUserInfo(@NotNull Connection connection, int id, String name, String password) {
        log("移除指定数据: id = " + id + " name = " + name + " password = " + password);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {

            try {
                statement = connection.prepareStatement(
                        "delete from login_user where id = ? or name = ? or password = ?"
                );
                statement.setInt(1, id);
                statement.setString(2, name == null ? "" : name);
                statement.setString(3, password == null ? "" : password);
                statement.executeUpdate();
                log("移除成功");
            } catch (SQLException e) {
                e.printStackTrace();
                log("移除失败");
                return false;
            } finally {
                sqlClose(null, statement, resultSet);
            }

        }
        return true;
    }

     boolean updataUser(@NotNull Connection connection, int id, String name, String password) {
        log("更新指定用户数据 : id = " + id + " name = " + name + " password = " + password);
        PreparedStatement statement = null;
        if (connection != null) {

            try {
                statement = connection.prepareStatement(
                        "update login_user set name = ? , password = ? where id = ?"
                );

                statement.setInt(3, id);
                statement.setString(1, name == null ? "null" : name);
                statement.setString(2, password == null ? "null" : password);
                statement.executeUpdate();
                log("更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
                log("更新失败");
                return false;
            } finally {
                sqlClose(null, statement, null);
            }
        }
        return true;
    }

    /**
     * 查询所有用户数据
     *
     * @param connection
     */
     boolean queryAllUser(@NotNull Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(
                        "SELECT id, name, password from login_user"
                );

                log("id\t\tname\t\tpassword");
                while (resultSet.next()) {
                    log(
                            resultSet.getInt("id")
                                    + "\t" + "\t" + resultSet.getString("name")
                                    + "\t" + "\t" + resultSet.getString("password")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                sqlClose(null, statement, resultSet);
            }
        }
        return true;
    }


    private void log(String text) {
        System.out.println(text);
    }

}
