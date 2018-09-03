package com.immortal.half.wu.dao.impls;

import com.immortal.half.wu.bean.RegisterUserBean;
import com.immortal.half.wu.dao.interfaces.SQLDao;
import com.sun.webkit.network.data.Handler;
import jdk.internal.org.objectweb.asm.Handle;

import java.sql.Connection;

public class RegisterUserDao implements SQLDao<RegisterUserBean> {

    private String tableName = "";

    public RegisterUserDao(Connection connection) {

    }

    @Override
    public void addToSQL(Connection connection, RegisterUserBean bean) throws Exception {

    }

    @Override
    public void deleteFromSQL(Connection connection, RegisterUserBean bean) throws Exception {

    }

    @Override
    public void updataToSQL(Connection connection, RegisterUserBean bean) throws Exception {

    }

    @Override
    public void selectFromSQL(Connection connection, RegisterUserBean bean) throws Exception {

    }
}
