package com.gabriel.equalscase.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.gabriel.equalscase.model.visamaster.DetalheMasterVisa;
import com.gabriel.equalscase.model.visamaster.HeaderMasterVisa;
import com.gabriel.equalscase.model.visamaster.TrailerMasterVisa;
import com.gabriel.equalscase.parser.LeitorMasterVisa;
import com.gabriel.equalscase.parser.LeitorVendas;

@Controller
public class ArquivoWebController {

    // Mappers de cada entidade
    private final HeaderMapper<HeaderMasterVisa> headerMapper;
    private final DetalheMapper<DetalheMasterVisa> detalheMapper;
    private final TrailerMapper<TrailerMasterVisa> trailerMapper;

    // Construtor do Controller, que recebe os mappers como parâmetro
    public ArquivoWebController(HeaderMapperMasterVisa headerMapper,
                                DetalheMapperMasterVisa detalheMapper,
                                TrailerMapperMasterVisa trailerMapper) {
        this.headerMapper = headerMapper;
        this.detalheMapper = detalheMapper;
        this.trailerMapper = trailerMapper;
    }

    // Define que a rota principal vai retornar um html para que o arquivo seja submetido
    @GetMapping("/")
    public String form() {
        return "upload-form";
    }

    // Rota que é chamada no momento em que arquivo é enviado
    @PostMapping("/processar")
    public String processar(@RequestParam("file") MultipartFile file, Model model) {
        Header header = new HeaderMasterVisa(); // variável que guarda o Header
        Trailer trailer = null; // variável que guarda o Trailer
        List<Detalhe> detalhes = new ArrayList<>(); // Variável para guardar os detalhes

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            LeitorVendas leitor = new LeitorMasterVisa();

            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue; // Se a linha estiver vazia, pula

                switch (linha.charAt(0)) { // Olha o primeiro caracter da linha para verificar qual o tipo
                    case '0':
                        header = leitor.lerHeader(linha); // Manda a linha lida para o parser do Header
                        headerMapper.insert((HeaderMasterVisa) header); // Chama o Mapper que por sua vez vai executar o comando no XML
                        break;
                    case '1':
                        Detalhe detalhe = leitor.lerDetalhe(linha);// Manda a linha lida para o parser do Detalhe
                        detalheMapper.insert((DetalheMasterVisa) detalhe); // Chama o Mapper que por sua vez vai executar o comando no XML
                        detalhes.add(detalhe);
                        break;
                    case '9':
                        trailer = leitor.lerTrailer(linha); // Manda a linha lida para o parser do Trailer
                        trailerMapper.insert((TrailerMasterVisa) trailer); // Chama o Mapper que por sua vez vai executar o comando no XML
                        break;
                }
            }

            model.addAttribute("header", header);
            model.addAttribute("detalhes", detalhes);
            model.addAttribute("trailer", trailer);

            return "resultado";

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar: " + e.getMessage());
            return "upload-form";
        }
    }
}
