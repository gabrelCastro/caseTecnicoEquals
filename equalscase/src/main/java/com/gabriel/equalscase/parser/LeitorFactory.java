package com.gabriel.equalscase.parser;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

@Component
public class LeitorFactory {
    
    private final Map<String, LeitorVendas<?, ?, ?>> leitores;

    public LeitorFactory(Map<String, LeitorVendas<?, ?, ?>> leitores) {
        this.leitores = leitores;
    }

    @SuppressWarnings("unchecked")
    public <H extends Header, D extends Detalhe, T extends Trailer>
    LeitorVendas<H, D, T> getLeitor(String bandeira) {
        LeitorVendas<?, ?, ?> leitor = leitores.get(bandeira+"Leitor");
        if (leitor == null) {
            throw new IllegalArgumentException("Bandeira não suportada: " + bandeira);
        }
        return (LeitorVendas<H, D, T>) leitor;
    }
}