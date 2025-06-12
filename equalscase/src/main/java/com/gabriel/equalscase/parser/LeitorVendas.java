package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

public interface LeitorVendas<H extends Header, D extends Detalhe, T extends Trailer> {
    H lerHeader(String linha);
    D lerDetalhe(String linha);
    T lerTrailer(String linha);
}