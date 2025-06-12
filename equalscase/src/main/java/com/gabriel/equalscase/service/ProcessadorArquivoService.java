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

@Service
public class ProcessadorArquivoService {

    private final Map<String, HeaderMapper<? extends Header>> headerMappers;
    private final Map<String, DetalheMapper<? extends Detalhe>> detalheMappers;
    private final Map<String, TrailerMapper<? extends Trailer>> trailerMappers;

    private final LeitorFactory leitorFactory;

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

    public <H extends Header, D extends Detalhe, T extends Trailer>
    void processarArquivo(MultipartFile file, String bandeira) throws Exception {

        LeitorVendas<H, D, T> leitor = leitorFactory.getLeitor(bandeira);
        HeaderMapper<H> headerMapper = (HeaderMapper<H>) headerMappers.get(bandeira + "HeaderMapper");
        DetalheMapper<D> detalheMapper = (DetalheMapper<D>) detalheMappers.get(bandeira+ "DetalheMapper");
        TrailerMapper<T> trailerMapper = (TrailerMapper<T>) trailerMappers.get(bandeira+ "TrailerMapper");

        if (headerMapper == null || detalheMapper == null || trailerMapper == null) {
            throw new IllegalArgumentException("Bandeira não suportada: " + bandeira);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue;

                char tipo = linha.charAt(0);
                switch (tipo) {
                    case '0' -> headerMapper.insert(leitor.lerHeader(linha));
                    case '1' -> detalheMapper.insert(leitor.lerDetalhe(linha));
                    case '9' -> trailerMapper.insert(leitor.lerTrailer(linha));
                }
            }
        }
    }


}
