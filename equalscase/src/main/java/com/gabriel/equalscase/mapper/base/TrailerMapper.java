package com.gabriel.equalscase.mapper.base;

import java.util.List;

import com.gabriel.equalscase.model.base.Trailer;

/**
 * Interface base de mapeamento MyBatis para operações com a entidade Trailer.
 *
 * @param <T> tipo concreto que estende Trailer (ex: TrailerMasterVisa)
 *
 * Essa interface define operações básicas de persistência e recuperação de trailers,
 * usados como parte do processamento de arquivos de vendas.
 */
public interface TrailerMapper<T extends Trailer> {

    /**
     * Insere um novo registro do tipo Trailer no banco de dados.
     *
     * @param trailer instância de T a ser inserida
     */
    void insert(T trailer);

    /**
     * Retorna todos os trailers armazenados no banco.
     *
     * @return lista de trailers
     */
    List<T> findAll();

    /**
     * Recupera o primeiro registro de trailer encontrado na tabela.
     * 
     * Geralmente utilizado quando se espera apenas um trailer por arquivo processado.
     *
     * @return primeiro Trailer encontrado
     */
    T findFirst();
}
