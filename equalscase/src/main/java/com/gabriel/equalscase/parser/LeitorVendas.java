package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

public interface LeitorVendas {
    Header lerHeader(String linha);
    Detalhe lerDetalhe(String linha);
    Trailer lerTrailer(String linha);
}
