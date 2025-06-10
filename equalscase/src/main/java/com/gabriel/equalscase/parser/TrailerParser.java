package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.Trailer;

public class TrailerParser {

    public static Trailer parse(String linha) {
        Trailer t = new Trailer();
        t.setTipoRegistro(linha.substring(0, 1));
        t.setTotalRegistro(Integer.parseInt(linha.substring(1, 12)));
        t.setReservado(linha.substring(12, 530));
        return t;
    }
}
