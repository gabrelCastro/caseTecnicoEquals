package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.HeaderMapper;
import com.gabriel.equalscase.model.visamaster.HeaderMasterVisa;


@Mapper
@Component("mastervisaHeaderMapper")
public interface HeaderMapperMasterVisa extends HeaderMapper<HeaderMasterVisa>{
}
