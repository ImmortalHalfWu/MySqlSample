package com.immortal.half.wu.dao.interfaces;

import com.sun.istack.internal.NotNull;

import java.sql.Connection;

public interface SQLDao<T> {
    void addToSQL(@NotNull Connection connection, T bean) throws Exception;
    void deleteFromSQL(@NotNull Connection connection, T bean) throws Exception;
    void updataToSQL(@NotNull Connection connection, T bean) throws Exception;
    void selectFromSQL(@NotNull Connection connection, T bean) throws Exception;
}
