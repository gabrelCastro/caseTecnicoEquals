package com.gabriel.equalscase.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.equalscase.mapper.base.DetalheMapper;
import com.gabriel.equalscase.mapper.base.HeaderMapper;
import com.gabriel.equalscase.mapper.base.TrailerMapper;
import com.gabriel.equalscase.model.base.Detalhe;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;
import com.gabriel.equalscase.parser.LeitorFactory;
import com.gabriel.equalscase.parser.LeitorVendas;

/**
 * Serviço responsável por processar o arquivo enviado e persistir os dados lidos.
 *
 * Esse serviço identifica qual leitor e quais mapeadores (mappers) utilizar com base
 * na bandeira fornecida (ex: "mastervisa"), usando para isso uma {@link LeitorFactory}
 * e três mapas de mappers: {@link HeaderMapper}, {@link DetalheMapper}, {@link TrailerMapper}.
 *
 * A leitura do arquivo é feita linha por linha, identificando o tipo de registro:
 * - Tipo 0 → header
 * - Tipo 1 → detalhe
 * - Tipo 9 → trailer
 *
 * Os registros lidos são convertidos em objetos de domínio e salvos no banco
 * utilizando os mapeadores do MyBatis.
 */
@Service
public class ProcessadorArquivoService {

    private final Map<String, HeaderMapper<? extends Header>> headerMappers;
    private final Map<String, DetalheMapper<? extends Detalhe>> detalheMappers;
    private final Map<String, TrailerMapper<? extends Trailer>> trailerMappers;

    private final LeitorFactory leitorFactory;

    /**
     * Construtor com injeção de dependências pelo Spring.
     *
     * @param headerMappers    mapa com todos os mappers de Header registrados no contexto
     * @param detalheMappers   mapa com todos os mappers de Detalhe registrados no contexto
     * @param trailerMappers   mapa com todos os mappers de Trailer registrados no contexto
     * @param leitorFactory    fábrica responsável por retornar o leitor correto baseado na bandeira
     */
    public ProcessadorArquivoService(
            Map<String, HeaderMapper<? extends Header>> headerMappers,
            Map<String, DetalheMapper<? extends Detalhe>> detalheMappers,
            Map<String, TrailerMapper<? extends Trailer>> trailerMappers,
            LeitorFactory leitorFactory) {

        this.headerMappers = headerMappers;
        this.detalheMappers = detalheMappers;
        this.trailerMappers = trailerMappers;
        this.leitorFactory = leitorFactory;
    }

    /**
     * Processa um arquivo texto contendo registros de vendas.
     *
     * O método identifica qual leitor utilizar com base na bandeira,
     * então lê cada linha do arquivo e, conforme o tipo do registro (0, 1 ou 9),
     * chama o parser correspondente e insere no banco usando o mapper apropriado.
     *
     * @param file     arquivo enviado pelo usuário (formato texto, MultipartFile)
     * @param bandeira identificador da bandeira (ex: "mastervisa")
     * @param <H>      tipo de Header específico
     * @param <D>      tipo de Detalhe específico
     * @param <T>      tipo de Trailer específico
     *
     * @throws Exception se houver erro de leitura ou se a bandeira não for suportada
     */
    public <H extends Header, D extends Detalhe, T extends Trailer>
    void processarArquivo(MultipartFile file, String bandeira) throws Exception {

        // Recupera dinamicamente o leitor correto com base na bandeira
        LeitorVendas<H, D, T> leitor = leitorFactory.getLeitor(bandeira);

        // Recupera os mappers para persistir os dados no banco
        HeaderMapper<H> headerMapper = (HeaderMapper<H>) headerMappers.get(bandeira + "HeaderMapper");
        DetalheMapper<D> detalheMapper = (DetalheMapper<D>) detalheMappers.get(bandeira + "DetalheMapper");
        TrailerMapper<T> trailerMapper = (TrailerMapper<T>) trailerMappers.get(bandeira + "TrailerMapper");

        // Verifica se todos os mappers estão disponíveis
        if (headerMapper == null || detalheMapper == null || trailerMapper == null) {
            throw new IllegalArgumentException("Bandeira não suportada: " + bandeira);
        }

        // Lê o conteúdo do arquivo linha por linha
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue;

                char tipo = linha.charAt(0); // Tipo do registro: 0, 1 ou 9

                // Chama o leitor correspondente e insere no banco
                switch (tipo) {
                    case '0' -> headerMapper.insert(leitor.lerHeader(linha));
                    case '1' -> detalheMapper.insert(leitor.lerDetalhe(linha));
                    case '9' -> trailerMapper.insert(leitor.lerTrailer(linha));
                    default -> {
                        // Ignora ou lança exceção se o tipo for desconhecido
                        // throw new IllegalArgumentException("Tipo de registro inválido: " + tipo);
                    }
                }
            }
        }
    }
}
