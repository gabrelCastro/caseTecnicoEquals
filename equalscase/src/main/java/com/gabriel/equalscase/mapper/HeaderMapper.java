package com.gabriel.equalscase.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gabriel.equalscase.model.Header;


@Mapper
public interface HeaderMapper {
    void insert(Header header);
    Header findById(int id);
}
