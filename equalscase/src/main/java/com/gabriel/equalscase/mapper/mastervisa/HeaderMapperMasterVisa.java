package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.HeaderMapper;
import com.gabriel.equalscase.model.visamaster.HeaderMasterVisa;

/**
 * Mapper MyBatis específico para o header dos arquivos da bandeira Master/Visa.
 *
 * Essa interface especializa {@link HeaderMapper} com o tipo {@link HeaderMasterVisa},
 * e será usada pelo MyBatis e Spring para persistência e recuperação dos dados de cabeçalho.
 *
 * Anotações:
 * - {@code @Mapper}: informa ao MyBatis que essa interface contém mapeamentos SQL.
 * - {@code @Component("mastervisaHeaderMapper")}: registra o bean com nome específico no contexto do Spring,
 *   permitindo injeção dinâmica via chave composta no controller (ex: "mastervisaHeaderMapper").
 */
@Mapper
@Component("mastervisaHeaderMapper")
public interface HeaderMapperMasterVisa extends HeaderMapper<HeaderMasterVisa> {
    // Os métodos genéricos já são herdados de HeaderMapper
}
