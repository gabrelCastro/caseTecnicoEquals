package com.gabriel.equalscase.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gabriel.equalscase.model.base.Detalhe;

public interface DetalheMapper<T extends Detalhe> {
    void insert(T detalhe);
    List<T> findAll();
    List<T> buscarPaginado(@Param("dataInicial") String dataInicial,
                                           @Param("dataFinal") String dataFinal,
                                           @Param("instituicao") String instituicao,
                                           @Param("offset") int offset,
                                           @Param("tamanho") int tamanho);

    int contarFiltrado(@Param("dataInicial") String dataInicial,
                    @Param("dataFinal") String dataFinal,
                    @Param("instituicao") String instituicao);
}