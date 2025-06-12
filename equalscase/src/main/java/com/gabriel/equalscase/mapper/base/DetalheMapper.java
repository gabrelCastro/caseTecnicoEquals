package com.gabriel.equalscase.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gabriel.equalscase.model.base.Detalhe;

/**
 * Interface base para mapeamento MyBatis de operações relacionadas à entidade Detalhe.
 *
 * @param <T> Tipo específico de Detalhe (por exemplo, DetalheMasterVisa)
 *
 * Esta interface define métodos genéricos que os mappers específicos de bandeira devem implementar.
 * O uso de @Param garante que os parâmetros sejam corretamente mapeados no XML.
 */
public interface DetalheMapper<T extends Detalhe> {

    /**
     * Insere um registro do tipo Detalhe no banco de dados.
     *
     * @param detalhe instância de T a ser inserida
     */
    void insert(T detalhe);

    /**
     * Retorna todos os registros da tabela de Detalhes.
     *
     * @return lista de detalhes
     */
    List<T> findAll();

    /**
     * Busca registros filtrados por data e instituição, com suporte a paginação.
     *
     * @param dataInicial   data mínima da transação (pode ser null)
     * @param dataFinal     data máxima da transação (pode ser null)
     * @param instituicao   nome da instituição financeira (pode ser null)
     * @param offset        deslocamento para paginação (ex: 0, 25, 50...)
     * @param tamanho       quantidade de registros por página
     * @return lista paginada de detalhes que atendem aos filtros
     */
    List<T> buscarPaginado(@Param("dataInicial") String dataInicial,
                           @Param("dataFinal") String dataFinal,
                           @Param("instituicao") String instituicao,
                           @Param("offset") int offset,
                           @Param("tamanho") int tamanho);

    /**
     * Conta o número total de registros que atendem aos filtros.
     * Útil para calcular a quantidade de páginas no frontend.
     *
     * @param dataInicial   data mínima da transação (pode ser null)
     * @param dataFinal     data máxima da transação (pode ser null)
     * @param instituicao   nome da instituição financeira (pode ser null)
     * @return número total de registros que atendem aos critérios
     */
    int contarFiltrado(@Param("dataInicial") String dataInicial,
                       @Param("dataFinal") String dataFinal,
                       @Param("instituicao") String instituicao);
}
