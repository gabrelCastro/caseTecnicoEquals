package com.gabriel.equalscase.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.equalscase.mapper.base.DetalheMapper;
import com.gabriel.equalscase.mapper.base.HeaderMapper;
import com.gabriel.equalscase.mapper.base.TrailerMapper;
import com.gabriel.equalscase.mapper.mastervisa.DetalheMapperMasterVisa;
import com.gabriel.equalscase.mapper.mastervisa.HeaderMapperMasterVisa;
import com.gabriel.equalscase.mapper.mastervisa.TrailerMapperMasterVisa;
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

    public ProcessadorArquivoService(
            HeaderMapperMasterVisa headerMasterVisa,
            DetalheMapperMasterVisa detalheMasterVisa,
            TrailerMapperMasterVisa trailerMasterVisa
    ) {
        this.headerMappers = Map.of("mastervisa", headerMasterVisa);
        this.detalheMappers = Map.of("mastervisa", detalheMasterVisa);
        this.trailerMappers = Map.of("mastervisa", trailerMasterVisa);
    }


    public void processarArquivo(MultipartFile file, String bandeira) throws Exception {
        LeitorVendas leitor = LeitorFactory.getLeitor(bandeira);

        HeaderMapper<Header> headerMapper = (HeaderMapper<Header>) headerMappers.get(bandeira);
        DetalheMapper<Detalhe> detalheMapper = (DetalheMapper<Detalhe>) detalheMappers.get(bandeira);
        TrailerMapper<Trailer> trailerMapper = (TrailerMapper<Trailer>) trailerMappers.get(bandeira);

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