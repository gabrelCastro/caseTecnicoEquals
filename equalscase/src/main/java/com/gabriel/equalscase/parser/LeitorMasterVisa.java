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
 * Esta classe faz parte da lógica de leitura de arquivos no padrão definido pelas bandeiras
 * Visa/MasterCard, onde cada linha do arquivo representa um tipo de registro:
 * - Tipo 0: Header
 * - Tipo 1: Detalhe
 * - Tipo 9: Trailer
 *
 * Responsabilidade principal:
 * - Interpretar linhas do arquivo e convertê-las em objetos Java específicos (`HeaderMasterVisa`,
 *   `DetalheMasterVisa` e `TrailerMasterVisa`), preenchendo os dados conforme as posições fixas
 *   de cada campo no layout.
 *
 * Contexto de uso:
 * - Utilizada durante o processo de leitura e importação de arquivos texto, no momento em que
 *   as informações precisam ser transformadas em objetos de domínio para persistência no banco
 *   via MyBatis.
 *
 * Relacionamentos:
 * - Implementa a interface LeitorVendas, que define os métodos padrão de leitura de
 *   registros do tipo header, detalhe e trailer.
 * - Utiliza os modelos definidos em com.gabriel.equalscase.model.visamaster.
 *
 * Observações:
 * - Os campos de data e hora são convertidos utilizando `DateTimeFormatter`.
 * - Valores monetários são convertidos para `BigDecimal` com precisão de 2 casas decimais.
 * - A leitura respeita o comprimento fixo da linha conforme o layout especificado.
 *
 * Autor: Gabriel Ferreira
 */

@Component("mastervisaLeitor")
public class LeitorMasterVisa implements LeitorVendas<HeaderMasterVisa, DetalheMasterVisa, TrailerMasterVisa>{

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");

    @Override
    public HeaderMasterVisa lerHeader(String linha) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        HeaderMasterVisa h = new HeaderMasterVisa();

        h.setTipoRegistro(linha.substring(0, 1));
        h.setEstabelecimento(Long.parseLong(linha.substring(1, 11)));
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

    @Override
    public DetalheMasterVisa lerDetalhe(String linha) {
        DetalheMasterVisa d = new DetalheMasterVisa();

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

    @Override
    public TrailerMasterVisa lerTrailer(String linha) {
        TrailerMasterVisa t = new TrailerMasterVisa();
        t.setTipoRegistro(linha.substring(0, 1));
        t.setTotalRegistro(Integer.parseInt(linha.substring(1, 12)));
        t.setReservado(linha.substring(12, 530));
        return t;
    }


    private BigDecimal parseDecimal(String value) {
        value = value.trim();
        if (value.isEmpty() || !value.matches("\\d+")) {
            return BigDecimal.ZERO; 
        }
        return new BigDecimal(value).movePointLeft(2);
    }

}
