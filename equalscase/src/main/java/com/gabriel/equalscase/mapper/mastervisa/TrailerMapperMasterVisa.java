package com.gabriel.equalscase.mapper.mastervisa;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gabriel.equalscase.mapper.base.TrailerMapper;
import com.gabriel.equalscase.model.visamaster.TrailerMasterVisa;

/**
 * Mapper MyBatis específico para o trailer dos arquivos da bandeira Master/Visa.
 *
 * Essa interface especializa {@link TrailerMapper} usando o tipo {@link TrailerMasterVisa},
 * permitindo a persistência e recuperação de dados de trailer com MyBatis.
 *
 * Anotações:
 * - {@code @Mapper}: permite que o MyBatis gere implementações dessa interface automaticamente.
 * - {@code @Component("mastervisaTrailerMapper")}: registra esse mapper como um bean do Spring,
 *   acessível por chave "mastervisaTrailerMapper" — ideal para injeção dinâmica via mapa.
 */
@Mapper
@Component("mastervisaTrailerMapper")
public interface TrailerMapperMasterVisa extends TrailerMapper<TrailerMasterVisa> {
    // Os métodos são herdados de TrailerMapper e não precisam ser redefinidos
}
