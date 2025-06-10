package com.gabriel.equalscase.parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.gabriel.equalscase.model.Detalhe;

public class DetalheParser {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");

    public static Detalhe parse(String linha) {
        Detalhe d = new Detalhe();

        d.setTipoRegistro(linha.substring(0, 1));
        d.setEstabelecimento(Long.parseLong(linha.substring(1, 11)));
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

    private static BigDecimal parseDecimal(String value) {
        value = value.trim();
        if (value.isEmpty() || !value.matches("\\d+")) {
            return BigDecimal.ZERO; 
        }
        return new BigDecimal(value).movePointLeft(2);
    }
}
