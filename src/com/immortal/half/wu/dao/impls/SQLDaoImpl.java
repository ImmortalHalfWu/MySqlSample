package com.immortal.half.wu.dao.impls;

import com.alibaba.fastjson.JSONArray;
import com.immortal.half.wu.dao.utils.DaoUtil;
import com.immortal.half.wu.dao.interfaces.SQLDao;
import com.immortal.half.wu.dao.utils.SqlUtil;

import java.sql.Connection;

public class SQLDaoImpl implements SQLDao {

    private static SQLDaoImpl sqlDao;

    private SQLDaoImpl() {}

    public static SQLDaoImpl getInstance() {
        if (sqlDao == null) {
            synchronized (SQLDaoImpl.class) {
                sqlDao = new SQLDaoImpl();
            }
        }
        return sqlDao;
    }

    @Override
    public void addToSQL(Connection connection, String tableName, Object bean) throws Exception {
        SqlUtil.insertObjectToSQL(
                connection,
                DaoUtil.object2SqlBean(tableName, bean)
        );
    }

    @Override
    public void deleteFromSQL(Connection connection, String tableName, Object bean) throws Exception {
        SqlUtil.delObjectToSQL(connection, DaoUtil.object2SqlBean(tableName, bean));
    }

    @Override
    public void updataToSQL(Connection connection, String tableName, Object newBean, Object oldBean) throws Exception {
        SqlUtil.updataObjectToSQL(
                connection,
                DaoUtil.object2SqlBean(tableName, newBean),
                DaoUtil.object2SqlBean(tableName, oldBean)
        );
    }

    @Override
    public JSONArray selectFromSQL(Connection connection, String tableName, Object bean) throws Exception {
        return SqlUtil.selectObjectToSQL(connection, DaoUtil.object2SqlBean(tableName, bean));
    }
}
