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

/**
 * Controller responsável por lidar com a interface web para upload e visualização de arquivos de vendas.
 * 
 * Este controlador possui três endpoints principais:
 * - "/"          → exibe o formulário de upload
 * - "/processar" → processa o arquivo enviado
 * - "/dados"     → exibe os dados processados em formato paginado e filtrável
 * 
 * Os mappers para diferentes "bandeiras" (como mastervisa) são resolvidos dinamicamente por nome via Map<String, Mapper>.
 */
@Controller
public class ArquivoWebController {

    private final ProcessadorArquivoService processadorArquivoService;
    private final Map<String, HeaderMapper<?>> headerMappers;
    private final Map<String, DetalheMapper<?>> detalheMappers;
    private final Map<String, TrailerMapper<?>> trailerMappers;

    /**
     * Construtor com injeção de dependência.
     * O Spring injeta os mapeadores disponíveis, indexados por nome do Bean.
     */
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

    /**
     * Rota GET para exibir o formulário de upload.
     * @param model Modelo usado para carregar dados na view (neste caso, não utilizado)
     * @return nome do template HTML (upload-form.html)
     */
    @GetMapping("/")
    public String form(Model model) {
        return "upload-form";
    }

    /**
     * Rota POST que processa o arquivo enviado pelo usuário.
     * @param file Arquivo do tipo Multipart enviado via formulário
     * @param bandeira String identificando a bandeira (ex: "mastervisa")
     * @param model Modelo para exibir mensagens de erro se necessário
     * @return redireciona para "/dados" em caso de sucesso, ou recarrega o formulário em caso de erro
     */
    @PostMapping("/processar")
    public String processar(@RequestParam("file") MultipartFile file,
                            @RequestParam("bandeira") String bandeira,
                            Model model) {
        try {
            processadorArquivoService.processarArquivo(file, bandeira.toLowerCase());
            return "redirect:/dados"; // Sucesso: redireciona para exibição dos dados
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar: " + e.getMessage());
            model.addAttribute("bandeiras", List.of("mastervisa")); // Lista de bandeiras para exibir no form
            return "upload-form"; // Erro: volta para o formulário
        }
    }

    /**
     * Rota GET para exibir os dados processados, com suporte a paginação e filtros por data e instituição.
     * 
     * @param pagina número da página (default = 0)
     * @param dataInicial filtro opcional para data de início
     * @param dataFinal filtro opcional para data de fim
     * @param instituicao filtro opcional por instituição financeira
     * @param bandeira define qual conjunto de mappers será usado
     * @param model modelo para injetar os dados que serão renderizados na view
     * @return nome da view (resultado.html)
     */
    @GetMapping("/dados")
    public String verDados(@RequestParam(defaultValue = "0") int pagina,
                           @RequestParam(required = false) String dataInicial,
                           @RequestParam(required = false) String dataFinal,
                           @RequestParam(required = false) String instituicao,
                           @RequestParam(defaultValue = "mastervisa") String bandeira,
                           Model model) {

        // Configuração da paginação
        int tamanhoPagina = 25;
        int offset = pagina * tamanhoPagina;

        // Constrói as chaves que serão usadas para buscar os mappers da bandeira escolhida
        String hKey = bandeira + "HeaderMapper";
        String dKey = bandeira + "DetalheMapper";
        String tKey = bandeira + "TrailerMapper";

        // Busca os mappers dinâmicos baseados na bandeira
        HeaderMapper<?> headerMapper = headerMappers.get(hKey);
        DetalheMapper<?> detalheMapper = detalheMappers.get(dKey);
        TrailerMapper<?> trailerMapper = trailerMappers.get(tKey);

        // Caso algum dos mappers não esteja registrado, exibe erro
        if (headerMapper == null || detalheMapper == null || trailerMapper == null) {
            model.addAttribute("erro", "Mapper da bandeira '" + bandeira + "' não encontrado.");
            return "upload-form";
        }

        // Executa as consultas no banco usando os mappers apropriados
        Header header = headerMapper.findFirst();
        Trailer trailer = trailerMapper.findFirst();
        List<?> detalhes = detalheMapper.buscarPaginado(dataInicial, dataFinal, instituicao, offset, tamanhoPagina);
        int total = detalheMapper.contarFiltrado(dataInicial, dataFinal, instituicao);

        // Preenche o modelo com os dados para a view
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
