package com.gabriel.equalscase.mapper.base;

import java.util.List;

import com.gabriel.equalscase.model.base.Trailer;

public interface TrailerMapper<T extends Trailer> {
    void insert(T trailer);
    List<T> findAll();
    Trailer findFirst();
}