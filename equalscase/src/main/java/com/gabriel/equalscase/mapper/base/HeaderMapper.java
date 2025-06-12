package com.gabriel.equalscase.mapper.base;

import com.gabriel.equalscase.model.base.Header;

/**
 * Interface base de mapeamento MyBatis para operações com a entidade Header.
 *
 * @param <T> tipo concreto que estende Header (ex: HeaderMasterVisa)
 *
 * Essa interface define as operações essenciais sobre headers de arquivos de vendas,
 * como inserção, busca por ID e recuperação do primeiro registro.
 */
public interface HeaderMapper<T extends Header> {

    /**
     * Insere um novo registro do tipo Header no banco de dados.
     *
     * @param header instância de T a ser inserida
     */
    void insert(T header);

    /**
     * Busca um Header pelo ID.
     *
     * @param id identificador primário no banco de dados
     * @return Header correspondente ao ID, ou null se não existir
     */
    T findById(int id);

    /**
     * Recupera o primeiro Header da tabela.
     * 
     * Este método é útil quando se sabe que haverá apenas um Header por arquivo processado.
     *
     * @return primeiro Header encontrado na tabela
     */
    Header findFirst();
}
