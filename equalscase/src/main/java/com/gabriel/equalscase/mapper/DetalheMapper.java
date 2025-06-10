package com.gabriel.equalscase.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gabriel.equalscase.model.Detalhe;

@Mapper
public interface DetalheMapper {
    void insert(Detalhe detalhe);
}