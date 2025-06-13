package com.gabriel.equalscase.parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.gabriel.equalscase.model.visamaster.DetalheMasterVisa;
import com.gabriel.equalscase.model.visamaster.HeaderMasterVisa;
import com.gabriel.equalscase.model.visamaster.TrailerMasterVisa;

/**
 * Implementação do parser para arquivos de vendas das bandeiras Visa e MasterCard.
 *
 * Essa classe interpreta linhas fixas de arquivos texto (layout padrão),
 * convertendo-as em objetos Java do tipo:
 * - {@link HeaderMasterVisa}
 * - {@link DetalheMasterVisa}
 * - {@link TrailerMasterVisa}
 *
 * Cada método lê uma linha do arquivo e extrai os dados com base em posições fixas.
 * Os dados lidos são tipados corretamente (datas, horários, valores monetários etc.).
 *
 * A classe é registrada como um bean do Spring com o nome "mastervisaLeitor"
 * e é usada dinamicamente pela {@link LeitorFactory}.
 *
 * Autor: Gabriel Ferreira
 */
@Component("mastervisaLeitor")
public class LeitorMasterVisa implements LeitorVendas<HeaderMasterVisa, DetalheMasterVisa, TrailerMasterVisa> {

    /** Formato padrão para datas no layout (ex: 20240612) */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** Formato padrão para horários no layout (ex: 150304) */
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * Lê uma linha de header (registro tipo 0) e converte em {@link HeaderMasterVisa}.
     *
     * @param linha linha do arquivo com 531 caracteres no mínimo
     * @return instância de HeaderMasterVisa preenchida com os campos extraídos
     */
    @Override
    public HeaderMasterVisa lerHeader(String linha) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        HeaderMasterVisa h = new HeaderMasterVisa();
        h.setTipoRegistro(linha.substring(0, 1));
        h.setEstabelecimento(linha.substring(1, 11));
        h.setDataGeracao(LocalDate.parse(linha.substring(11, 19), df));
        h.setPeriodoInicial(LocalDate.parse(linha.substring(19, 27), df));
        h.setPeriodoFinal(LocalDate.parse(linha.substring(27, 35), df));
        h.setSequencia(Integer.parseInt(linha.substring(35, 42)));
        h.setEmpresaAdquirente(linha.substring(42, 47).trim());
        h.setTipoExtrato(linha.substring(47, 49).trim());
        h.setFiller(linha.substring(49, 70).trim());
        h.setVersaoLayout(linha.substring(70, 73).trim());
        h.setVersaoRelease(linha.substring(73, 78).trim());
        h.setReservado(linha.length() >= 531 ? linha.substring(78, 531) : linha.substring(78));

        return h;
    }

    /**
     * Lê uma linha de detalhe (registro tipo 1) e converte em {@link DetalheMasterVisa}.
     *
     * @param linha linha do arquivo com os campos em posições fixas
     * @return instância de DetalheMasterVisa com os campos preenchidos
     */
    @Override
    public DetalheMasterVisa lerDetalhe(String linha) {
        DetalheMasterVisa d = new DetalheMasterVisa();

        d.setTipoRegistro(linha.substring(0, 1));
        d.setEstabelecimento(linha.substring(1, 11));
        d.setDataTransacao(LocalDate.parse(linha.substring(11, 19), DATE_FORMAT));
        d.setDataEvento(LocalDate.parse(linha.substring(19, 27), DATE_FORMAT));
        d.setHoraEvento(LocalTime.parse(linha.substring(27, 33), TIME_FORMAT));
        d.setTipoEvento(linha.substring(33, 35));
        d.setTipoTransacao(linha.substring(35, 37));
        d.setNumeroSerieLeitor(linha.substring(37, 45).trim());
        d.setCodigoTransacao(linha.substring(45, 77).trim());
        d.setCodigoPedido(linha.substring(77, 97).trim());
        d.setValorTotal(parseDecimal(linha.substring(97, 110)));
        d.setValorLiquido(parseDecimal(linha.substring(110, 123)));
        d.setPagamento(linha.substring(123, 124));
        d.setPlano(linha.substring(124, 126));
        d.setParcela(linha.substring(126, 128));
        d.setQuantidadeParcelas(linha.substring(128, 130));
        d.setDataPrevistaPagamento(LocalDate.parse(linha.substring(130, 138), DATE_FORMAT));
        d.setTaxaParcelamentoComprador(parseDecimal(linha.substring(138, 151)));
        d.setTarifaBoletoComprador(parseDecimal(linha.substring(151, 164)));
        d.setValorOriginalTransacao(parseDecimal(linha.substring(164, 177)));
        d.setTaxaParcelamentoVendedor(parseDecimal(linha.substring(177, 190)));
        d.setTaxaIntermediacao(parseDecimal(linha.substring(190, 203)));
        d.setTarifaBoletoVendedor(parseDecimal(linha.substring(203, 216)));
        d.setRepasseAplicacao(parseDecimal(linha.substring(216, 229)));
        d.setValorLiquidoTransacao(parseDecimal(linha.substring(229, 242)));
        d.setStatusPagamento(linha.substring(255, 257));
        d.setFiller(linha.substring(257, 259));
        d.setMeioPagamento(linha.substring(259, 261));
        d.setInstituicaoFinanceira(linha.substring(261, 291).trim());
        d.setCanalEntrada(linha.substring(291, 293));
        d.setLeitor(linha.substring(293, 295));
        d.setMeioCaptura(linha.substring(295, 297));
        d.setNumeroLogico(linha.substring(297, 329).trim());
        d.setNsu(linha.substring(329, 340).trim());
        d.setFiller2(linha.substring(340, 343));
        d.setCartaoBin(linha.substring(343, 349));
        d.setCartaoHolder(linha.substring(349, 353));
        d.setCodigoAutorizacao(linha.substring(353, 359));
        d.setCodigoCv(linha.substring(359, 391).trim());
        d.setReservado(linha.length() >= 530 ? linha.substring(391, 530) : linha.substring(391));
        return d;
    }

    /**
     * Lê uma linha de trailer (registro tipo 9) e converte em {@link TrailerMasterVisa}.
     *
     * @param linha linha do arquivo contendo os dados finais do lote
     * @return instância de TrailerMasterVisa preenchida
     */
    @Override
    public TrailerMasterVisa lerTrailer(String linha) {
        TrailerMasterVisa t = new TrailerMasterVisa();
        t.setTipoRegistro(linha.substring(0, 1));
        t.setTotalRegistro(Integer.parseInt(linha.substring(1, 12)));
        t.setReservado(linha.substring(12, 530));
        return t;
    }

    /**
     * Converte uma string numérica de valor fixo para {@link BigDecimal}, deslocando a vírgula duas casas.
     *
     * @param value string numérica (ex: "000000012345" → 123.45)
     * @return valor decimal com precisão de centavos
     */
    private BigDecimal parseDecimal(String value) {
        value = value.trim();
        if (value.isEmpty() || !value.matches("\\d+")) {
            return BigDecimal.ZERO; 
        }
        return new BigDecimal(value).movePointLeft(2); // Ex: 12345 → 123.45
    }
}
