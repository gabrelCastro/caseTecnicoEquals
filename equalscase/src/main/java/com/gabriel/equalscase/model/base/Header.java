package com.gabriel.equalscase.model.base;

import java.time.LocalDate;

/**
 * Classe base que representa o cabeçalho (header) de um arquivo de vendas.
 * 
 * O Header contém informações gerais sobre o arquivo, como o estabelecimento,
 * o período de referência, a data de geração, e metadados como sequência e versão do layout.
 * 
 * Essa classe pode ser estendida por classes específicas de bandeiras, como {@code HeaderMasterVisa}.
 */
public class Header {

    /** Identificador único do header no banco de dados */
    private Integer id;

    /** Tipo do registro — geralmente '0' para header */
    private String tipoRegistro;

    /** Código do estabelecimento ao qual o relatório pertence */
    private String estabelecimento;

    /** Data em que o arquivo foi gerado */
    private LocalDate dataGeracao;

    /** Data inicial do período de vendas incluído no relatório */
    private LocalDate periodoInicial;

    /** Data final do período de vendas incluído no relatório */
    private LocalDate periodoFinal;

    /** Número sequencial do arquivo (incremental por geração) */
    private Integer sequencia;

    /** Código ou nome da empresa adquirente responsável pelo processamento */
    private String empresaAdquirente;

    /** Tipo de extrato (ex: analítico, sintético) */
    private String tipoExtrato;

    /** Campo reservado para alinhamento de layout ou informações ignoradas */
    private String filler;

    /** Versão do layout utilizado no arquivo */
    private String versaoLayout;

    /** Versão do release associado ao layout */
    private String versaoRelease;

    /** Campo reservado para informações futuras ou de uso interno */
    private String reservado;

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public String getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }

    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }

    public LocalDate getPeriodoInicial() { return periodoInicial; }
    public void setPeriodoInicial(LocalDate periodoInicial) { this.periodoInicial = periodoInicial; }

    public LocalDate getPeriodoFinal() { return periodoFinal; }
    public void setPeriodoFinal(LocalDate periodoFinal) { this.periodoFinal = periodoFinal; }

    public Integer getSequencia() { return sequencia; }
    public void setSequencia(Integer sequencia) { this.sequencia = sequencia; }

    public String getEmpresaAdquirente() { return empresaAdquirente; }
    public void setEmpresaAdquirente(String empresaAdquirente) { this.empresaAdquirente = empresaAdquirente; }

    public String getTipoExtrato() { return tipoExtrato; }
    public void setTipoExtrato(String tipoExtrato) { this.tipoExtrato = tipoExtrato; }

    public String getFiller() { return filler; }
    public void setFiller(String filler) { this.filler = filler; }

    public String getVersaoLayout() { return versaoLayout; }
    public void setVersaoLayout(String versaoLayout) { this.versaoLayout = versaoLayout; }

    public String getVersaoRelease() { return versaoRelease; }
    public void setVersaoRelease(String versaoRelease) { this.versaoRelease = versaoRelease; }

    public String getReservado() { return reservado; }
    public void setReservado(String reservado) { this.reservado = reservado; }

}
