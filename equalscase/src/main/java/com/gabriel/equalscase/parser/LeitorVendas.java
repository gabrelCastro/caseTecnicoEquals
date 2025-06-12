package com.gabriel.equalscase.parser;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

public interface LeitorVendas {
    public Header lerHeader(String linha);
    public Detalhe lerDetalhe(String linha);
    public Trailer lerTrailer(String linha);
}
