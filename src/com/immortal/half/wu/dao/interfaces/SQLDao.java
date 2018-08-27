package com.immortal.half.wu.dao.interfaces;

import com.sun.istack.internal.NotNull;

import java.sql.Connection;

public interface SQLDao<T> {
    void add(@NotNull Connection connection, T bean) throws Exception;
    void del(@NotNull Connection connection, T bean) throws Exception;
    void updata(@NotNull Connection connection, T bean) throws Exception;
    void find(@NotNull Connection connection, T bean) throws Exception;
}
