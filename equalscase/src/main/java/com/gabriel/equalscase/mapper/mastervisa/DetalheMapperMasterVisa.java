package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.DetalheMapper;
import com.gabriel.equalscase.model.visamaster.DetalheMasterVisa;


@Mapper
@Component("mastervisaDetalheMapper")
public interface DetalheMapperMasterVisa extends DetalheMapper<DetalheMasterVisa>{
    
}