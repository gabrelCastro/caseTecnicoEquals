package com.gabriel.equalscase.mapper.base;

import java.util.List;

import com.gabriel.equalscase.model.base.Detalhe;

public interface DetalheMapper<T extends Detalhe> {
    void insert(T detalhe);
    List<T> findAll();
}