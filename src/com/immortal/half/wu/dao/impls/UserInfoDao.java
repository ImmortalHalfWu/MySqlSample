package com.immortal.half.wu.dao.impls;

import com.immortal.half.wu.bean.UserInfoBean;
import com.immortal.half.wu.dao.interfaces.SQLDao;

import java.sql.Connection;

public class UserInfoDao implements SQLDao<UserInfoBean> {


    /**
     * 注册用户
     * @param connection
     * @param bean
     * @throws Exception
     */
    @Override
    public void addToSQL(Connection connection, UserInfoBean bean) throws Exception {

    }


    /**
     * 移除用户
     * @param connection
     * @param bean
     * @throws Exception
     */
    @Override
    public void deleteFromSQL(Connection connection, UserInfoBean bean) throws Exception {
    }

    /**
     * 用户数据更新
     * @param connection
     * @param bean
     * @throws Exception
     */
    @Override
    public void updataToSQL(Connection connection, UserInfoBean bean) throws Exception {

    }

    /**
     * 查找用户
     * @param connection
     * @param bean
     * @throws Exception
     */
    @Override
    public void selectFromSQL(Connection connection, UserInfoBean bean) throws Exception {

    }
}
