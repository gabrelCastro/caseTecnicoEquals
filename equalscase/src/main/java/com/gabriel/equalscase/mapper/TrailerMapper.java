package com.gabriel.equalscase.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gabriel.equalscase.model.Trailer;

@Mapper
public interface TrailerMapper {
    void insert(Trailer trailer);
}
