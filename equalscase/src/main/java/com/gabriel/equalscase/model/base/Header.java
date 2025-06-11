package com.gabriel.equalscase.model.base;

import java.time.LocalDate;


public class Header {

    private Integer id;
    private String tipoRegistro;
    private Long estabelecimento;
    private LocalDate dataGeracao;
    private LocalDate periodoInicial;
    private LocalDate periodoFinal;
    private Integer sequencia;
    private String empresaAdquirente;
    private String tipoExtrato;
    private String filler;
    private String versaoLayout;
    private String versaoRelease;
    private String reservado;
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public Long getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(Long estabelecimento) { this.estabelecimento = estabelecimento; }

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
