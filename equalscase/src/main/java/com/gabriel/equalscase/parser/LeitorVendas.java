package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.Detalhe;
import com.gabriel.equalscase.model.Header;
import com.gabriel.equalscase.model.Trailer;

public interface LeitorVendas {
    Header lerHeader(String linha);
    Detalhe lerDetalhe(String linha);
    Trailer lerTrailer(String linha);
}
