package com.gabriel.equalscase.mapper.base;

import com.gabriel.equalscase.model.base.Header;

public interface HeaderMapper<T extends Header> {
    void insert(T header);
    T findById(int id);
}