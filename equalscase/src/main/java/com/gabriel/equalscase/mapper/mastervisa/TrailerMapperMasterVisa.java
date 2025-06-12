package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.TrailerMapper;
import com.gabriel.equalscase.model.visamaster.TrailerMasterVisa;

@Mapper
@Component("mastervisaTrailerMapper")
public interface TrailerMapperMasterVisa extends TrailerMapper<TrailerMasterVisa>{
}
