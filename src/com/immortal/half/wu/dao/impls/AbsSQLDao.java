package com.immortal.half.wu.dao.impls;

import com.immortal.half.wu.dao.interfaces.SQLDao;

import java.sql.Connection;

public class AbsSQLDao<T> implements SQLDao<T> {

    @Override
    public void add(Connection connection, T bean) throws Exception {

    }

    @Override
    public void del(Connection connection, T bean) throws Exception {

    }

    @Override
    public void updata(Connection connection, T bean) throws Exception {

    }

    @Override
    public void find(Connection connection, T bean) throws Exception {

    }
}
