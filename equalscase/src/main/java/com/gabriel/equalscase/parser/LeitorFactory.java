package com.gabriel.equalscase.parser;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;

/**
 * Fábrica de leitores de arquivos de vendas.
 * 
 * Esta classe seleciona dinamicamente a implementação de {@link LeitorVendas}
 * com base na bandeira informada (ex: "mastervisa"), permitindo abstração
 * e flexibilidade no processamento de diferentes layouts de arquivos.
 * 
 * Cada leitor é registrado como um bean com um nome padronizado no formato:
 * <pre>{@code @Component("mastervisaLeitor")}</pre>
 * 
 * A fábrica usa um {@code Map<String, LeitorVendas<?, ?, ?>>} injetado pelo Spring
 * contendo todos os leitores disponíveis no contexto da aplicação.
 */
@Component
public class LeitorFactory {

    /**
     * Mapa de leitores disponíveis, onde a chave é o nome do bean (ex: "mastervisaLeitor").
     * Os valores são instâncias de {@link LeitorVendas} registradas como componentes Spring.
     */
    private final Map<String, LeitorVendas<?, ?, ?>> leitores;

    /**
     * Construtor com injeção automática dos leitores registrados.
     * 
     * @param leitores mapa com os leitores registrados no Spring (via @Component)
     */
    public LeitorFactory(Map<String, LeitorVendas<?, ?, ?>> leitores) {
        this.leitores = leitores;
    }

    /**
     * Retorna o leitor correspondente à bandeira informada.
     * 
     * @param <H> tipo do Header da bandeira
     * @param <D> tipo do Detalhe da bandeira
     * @param <T> tipo do Trailer da bandeira
     * @param bandeira identificador da bandeira (ex: "mastervisa")
     * @return implementação de {@link LeitorVendas} adequada para a bandeira
     * @throws IllegalArgumentException se a bandeira não estiver registrada
     */
    @SuppressWarnings("unchecked")
    public <H extends Header, D extends Detalhe, T extends Trailer>
    LeitorVendas<H, D, T> getLeitor(String bandeira) {
        LeitorVendas<?, ?, ?> leitor = leitores.get(bandeira + "Leitor");

        if (leitor == null) {
            throw new IllegalArgumentException("Bandeira não suportada: " + bandeira);
        }

        // Cast é seguro porque o registro é controlado por nome de bean
        return (LeitorVendas<H, D, T>) leitor;
    }
}
