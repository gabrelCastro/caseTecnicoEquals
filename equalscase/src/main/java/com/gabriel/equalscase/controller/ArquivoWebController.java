package com.gabriel.equalscase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.equalscase.mapper.base.DetalheMapper;
import com.gabriel.equalscase.mapper.base.HeaderMapper;
import com.gabriel.equalscase.mapper.base.TrailerMapper;
import com.gabriel.equalscase.model.base.Header;
import com.gabriel.equalscase.model.base.Trailer;
import com.gabriel.equalscase.service.ProcessadorArquivoService;


@Controller
public class ArquivoWebController {

    private final ProcessadorArquivoService processadorArquivoService;
    private final Map<String, HeaderMapper<?>> headerMappers;
    private final Map<String, DetalheMapper<?>> detalheMappers;
    private final Map<String, TrailerMapper<?>> trailerMappers;

    public ArquivoWebController(
            ProcessadorArquivoService processadorArquivoService,
            Map<String, HeaderMapper<?>> headerMappers,
            Map<String, DetalheMapper<?>> detalheMappers,
            Map<String, TrailerMapper<?>> trailerMappers) {

        this.processadorArquivoService = processadorArquivoService;
        this.headerMappers = headerMappers;
        this.detalheMappers = detalheMappers;
        this.trailerMappers = trailerMappers;
    }

    @GetMapping("/")
    public String form(Model model) {
        return "upload-form";
    }

    @PostMapping("/processar")
    public String processar(@RequestParam("file") MultipartFile file,
                            @RequestParam("bandeira") String bandeira,
                            Model model) {
        try {
            processadorArquivoService.processarArquivo(file, bandeira.toLowerCase());
            return "redirect:/dados";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar: " + e.getMessage());
            model.addAttribute("bandeiras", List.of("mastervisa"));
            return "upload-form";
        }
    }

    @GetMapping("/dados")
    public String verDados(@RequestParam(defaultValue = "0") int pagina,
                           @RequestParam(required = false) String dataInicial,
                           @RequestParam(required = false) String dataFinal,
                           @RequestParam(required = false) String instituicao,
                           @RequestParam(defaultValue = "mastervisa") String bandeira,
                           Model model) {

        int tamanhoPagina = 25;
        int offset = pagina * tamanhoPagina;

        String hKey = bandeira + "HeaderMapper";
        String dKey = bandeira + "DetalheMapper";
        String tKey = bandeira + "TrailerMapper";

        HeaderMapper<?> headerMapper = headerMappers.get(hKey);
        DetalheMapper<?> detalheMapper = detalheMappers.get(dKey);
        TrailerMapper<?> trailerMapper = trailerMappers.get(tKey);

        if (headerMapper == null || detalheMapper == null || trailerMapper == null) {
            model.addAttribute("erro", "Mapper da bandeira '" + bandeira + "' não encontrado.");
            return "upload-form";
        }

        Header header = headerMapper.findFirst();
        Trailer trailer = trailerMapper.findFirst();
        List<?> detalhes = detalheMapper.buscarPaginado(dataInicial, dataFinal, instituicao, offset, tamanhoPagina);
        int total = detalheMapper.contarFiltrado(dataInicial, dataFinal, instituicao);

        model.addAttribute("header", header);
        model.addAttribute("trailer", trailer);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("instituicao", instituicao);
        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);
        model.addAttribute("temProximaPagina", (offset + tamanhoPagina) < total);
        model.addAttribute("bandeira", bandeira);

        return "resultado";
    }
}