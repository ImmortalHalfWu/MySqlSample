package com.immortal.half.wu.dao.impls;

import com.immortal.half.wu.dao.interfaces.SQLDao;

import java.sql.Connection;

public class AbsSQLDao<T> implements SQLDao<T> {

    @Override
    public void addToSQL(Connection connection, T bean) throws Exception {

    }

    @Override
    public void deleteFromSQL(Connection connection, T bean) throws Exception {

    }

    @Override
    public void updataToSQL(Connection connection, T bean) throws Exception {

    }

    @Override
    public void selectFromSQL(Connection connection, T bean) throws Exception {

    }
}
