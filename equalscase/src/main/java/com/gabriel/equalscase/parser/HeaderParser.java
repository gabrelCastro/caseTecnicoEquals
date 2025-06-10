package com.gabriel.equalscase.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.gabriel.equalscase.model.Header;

public class HeaderParser {

    public static Header parse(String linha) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        Header h = new Header();

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
}
