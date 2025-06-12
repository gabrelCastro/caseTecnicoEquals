package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.DetalheMapper;
import com.gabriel.equalscase.model.visamaster.DetalheMasterVisa;

/**
 * Mapper MyBatis específico para os detalhes da bandeira Master/Visa.
 * 
 * Essa interface estende a interface genérica {@link DetalheMapper} com o tipo {@link DetalheMasterVisa},
 * e é automaticamente registrada como um bean do Spring com o nome "mastervisaDetalheMapper".
 *
 * Anotações:
 * - {@code @Mapper}: informa ao MyBatis que essa é uma interface de mapeamento.
 * - {@code @Component("mastervisaDetalheMapper")}: registra o bean com nome específico para uso dinâmico.
 */
@Mapper
@Component("mastervisaDetalheMapper")
public interface DetalheMapperMasterVisa extends DetalheMapper<DetalheMasterVisa> {
    // Não é necessário sobrescrever os métodos, pois são herdados de DetalheMapper
}
