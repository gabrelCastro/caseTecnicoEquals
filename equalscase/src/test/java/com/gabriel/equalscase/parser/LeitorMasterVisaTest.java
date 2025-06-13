package com.gabriel.equalscase.parser;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.visamaster.DetalheMasterVisa;
import com.gabriel.equalscase.model.visamaster.TrailerMasterVisa;


public class LeitorMasterVisaTest {

    private final LeitorMasterVisa leitor = new LeitorMasterVisa();


    @Test
    void deveParsearHeader() {
        String linha = "012345678912018102320191023202010231234567FICTI01A                    002.002c";
        Header h = leitor.lerHeader(linha);

        assertThat(h.getTipoRegistro()).isEqualTo("0");
        assertThat(h.getDataGeracao()).isEqualTo(LocalDate.of(2018, 10, 23));
        assertThat(h.getPeriodoFinal()).isEqualTo(LocalDate.of(2020, 10, 23));
        assertThat(h.getPeriodoInicial()).isEqualTo(LocalDate.of(2019, 10, 23));
        assertThat(h.getSequencia()).isEqualTo(1234567);
        assertThat(h.getEmpresaAdquirente()).isEqualTo("FICTI");
    }

    @Test
    void deveParsearDetalhe() {
        StringBuilder sb = new StringBuilder();

        sb.append("1");                              // 1 - Tipo de registro
        sb.append("0000001234");                     // 10 - Estabelecimento
        sb.append("20240101");                       // 8 - Data inicial transação
        sb.append("20240103");                       // 8 - Data do evento
        sb.append("153045");                         // 6 - Hora do evento
        sb.append("01");                             // 2 - Tipo de evento
        sb.append("02");                             // 2 - Tipo de transação
        sb.append("LEITOR01");                       // 8 - Nº série leitor
        sb.append(String.format("%-32s", "TRANSACAO-XYZ"));    // 32 - Código da transação
        sb.append(String.format("%-20s", "PEDIDO-ABC"));       // 20 - Código do pedido
        sb.append("0000001234567");                  // 13 - Valor total
        sb.append("0000001122334");                  // 13 - Valor parcela/líquido
        sb.append("A");                              // 1 - Pagamento
        sb.append("03");                             // 2 - Plano
        sb.append("05");                             // 2 - Parcela
        sb.append("01");                             // 2 - Qtde parcelas
        sb.append("20240110");                       // 8 - Data prevista pagamento
        sb.append("0000000012345");                  // 13 - Taxa parcelamento comprador
        sb.append("0000000000123");                  // 13 - Tarifa boleto comprador
        sb.append("0000001234567");                  // 13 - Valor original transação
        sb.append("0000000011111");                  // 13 - Taxa parcelamento vendedor
        sb.append("0000000000100");                  // 13 - Taxa de intermediação
        sb.append("0000000000111");                  // 13 - Tarifa de intermediação
        sb.append("0000000000222");                  // 13 - Tarifa boleto vendedor
        sb.append("0000000000333");                  // 13 - Repasse aplicação
        sb.append("0000000000444");                  // 13 - Valor líquido da transação
        sb.append("01");                             // 2 - Status do pagamento
        sb.append("  ");                             // 2 - Filler
        sb.append("02");                             // 2 - Meio de pagamento
        sb.append(String.format("%-30s", "MASTERCARD"));       // 30 - Instituição/bandeira
        sb.append("WE");                             // 2 - Canal de entrada
        sb.append("01");                             // 2 - Leitor
        sb.append("03");                             // 2 - Meio de captura
        sb.append(String.format("%-32s", "1234567890TEFLOGICO")); // 32 - Número lógico
        sb.append(String.format("%-11s", "NSU123456"));           // 11 - NSU
        sb.append(String.format("%-3s", ""));                     // 3 - Filler
        sb.append(String.format("%-6s", "123456"));               // 6 - BIN
        sb.append(String.format("%-4s", "9999"));                 // 4 - Final do cartão
        sb.append(String.format("%-6s", "ABC123"));               // 6 - Código de autorização
        sb.append(String.format("%-32s", "CVCODE1234567890"));    // 32 - Código do CV
        sb.append("0".repeat(139));   // 139 - Reservado

        DetalheMasterVisa d = leitor.lerDetalhe(sb.toString());

        assertThat(d.getMeioPagamento()).isEqualTo("02");
        assertThat(d.getNumeroLogico()).isEqualTo("1234567890TEFLOGICO");
        assertThat(d.getTipoRegistro()).isEqualTo("1");
        assertThat(d.getEstabelecimento()).isEqualTo("0000001234");
        assertThat(d.getDataTransacao()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(d.getDataEvento()).isEqualTo(LocalDate.of(2024, 1, 3));
        assertThat(d.getHoraEvento()).isEqualTo(LocalTime.of(15, 30, 45));
        assertThat(d.getValorTotal()).isEqualByComparingTo("12345.67");
        assertThat(d.getValorLiquido()).isEqualByComparingTo("11223.34");
        assertThat(d.getInstituicaoFinanceira()).isEqualTo("MASTERCARD");
        assertThat(d.getMeioPagamento()).isEqualTo("02");
        assertThat(d.getNsu()).isEqualTo("NSU123456");
    }


    @Test
    void deveParsearTrailer() {
        StringBuilder sb = new StringBuilder();

        sb.append("9");                              // 1 - Tipo de registro
        sb.append("00000000025");                     // 10 - Estabelecimento
        sb.append("0".repeat(518));
        TrailerMasterVisa t = leitor.lerTrailer(sb.toString());
                

        assertThat(t.getTipoRegistro()).isEqualTo("9");
        assertThat(t.getTotalRegistro()).isEqualTo(25);
    }
    
}
