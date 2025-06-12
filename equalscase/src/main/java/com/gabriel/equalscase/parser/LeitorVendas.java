package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

/**
 * Interface genérica para leitura de arquivos de vendas com layout fixo.
 * 
 * Define os métodos básicos para interpretar linhas de um arquivo e convertê-las
 * em objetos de domínio, de acordo com o tipo de registro:
 * - {@code tipo 0}: Header
 * - {@code tipo 1}: Detalhe
 * - {@code tipo 9}: Trailer
 * 
 * Esta interface é parametrizada com os tipos específicos que estendem:
 * - {@link Header}
 * - {@link Detalhe}
 * - {@link Trailer}
 * 
 * @param <H> Tipo concreto de Header (ex: {@code HeaderMasterVisa})
 * @param <D> Tipo concreto de Detalhe (ex: {@code DetalheMasterVisa})
 * @param <T> Tipo concreto de Trailer (ex: {@code TrailerMasterVisa})
 *
 * Implementações dessa interface devem cuidar da extração dos dados com base
 * nas posições fixas da linha, conforme o layout de cada bandeira.
 */
public interface LeitorVendas<H extends Header, D extends Detalhe, T extends Trailer> {

    /**
     * Lê uma linha de header (registro tipo 0) e converte para um objeto de domínio.
     *
     * @param linha linha do arquivo representando o header
     * @return instância de {@code H}, representando o cabeçalho lido
     */
    H lerHeader(String linha);

    /**
     * Lê uma linha de detalhe (registro tipo 1) e converte para um objeto de domínio.
     *
     * @param linha linha do arquivo representando um detalhe de transação
     * @return instância de {@code D}, representando os dados da transação
     */
    D lerDetalhe(String linha);

    /**
     * Lê uma linha de trailer (registro tipo 9) e converte para um objeto de domínio.
     *
     * @param linha linha do arquivo representando o trailer
     * @return instância de {@code T}, representando o resumo do arquivo
     */
    T lerTrailer(String linha);
}
