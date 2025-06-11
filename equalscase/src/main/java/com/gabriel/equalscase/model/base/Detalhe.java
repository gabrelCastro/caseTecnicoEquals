package com.gabriel.equalscase.model.base;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Detalhe {
    private Integer id;
    private String tipoRegistro;
    private Long estabelecimento;
    private LocalDate dataTransacao;
    private LocalDate dataEvento;
    private LocalTime horaEvento;
    private String tipoEvento;
    private String tipoTransacao;
    private String numeroSerieLeitor;
    private String codigoTransacao;
    private String codigoPedido;
    private BigDecimal valorTotal;
    private BigDecimal valorLiquido;
    private String pagamento;
    private String plano;
    private String parcela;
    private String quantidadeParcelas;
    private LocalDate dataPrevistaPagamento;
    private BigDecimal taxaParcelamentoComprador;
    private BigDecimal tarifaBoletoComprador;
    private BigDecimal valorOriginalTransacao;
    private BigDecimal taxaParcelamentoVendedor;
    private BigDecimal taxaIntermediacao;
    private BigDecimal tarifaBoletoVendedor;
    private BigDecimal repasseAplicacao;
    private BigDecimal valorLiquidoTransacao;
    private String statusPagamento;
    private String filler;
    private String meioPagamento;
    private String instituicaoFinanceira;
    

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public Long getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(Long estabelecimento) { this.estabelecimento = estabelecimento; }

    public LocalDate getDataTransacao() { return dataTransacao; }
    public void setDataTransacao(LocalDate dataTransacao) { this.dataTransacao = dataTransacao; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public LocalTime getHoraEvento() { return horaEvento; }
    public void setHoraEvento(LocalTime horaEvento) { this.horaEvento = horaEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getTipoTransacao() { return tipoTransacao; }
    public void setTipoTransacao(String tipoTransacao) { this.tipoTransacao = tipoTransacao; }

    public String getNumeroSerieLeitor() { return numeroSerieLeitor; }
    public void setNumeroSerieLeitor(String numeroSerieLeitor) { this.numeroSerieLeitor = numeroSerieLeitor; }

    public String getCodigoTransacao() { return codigoTransacao; }
    public void setCodigoTransacao(String codigoTransacao) { this.codigoTransacao = codigoTransacao; }

    public String getCodigoPedido() { return codigoPedido; }
    public void setCodigoPedido(String codigoPedido) { this.codigoPedido = codigoPedido; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public BigDecimal getValorLiquido() { return valorLiquido; }
    public void setValorLiquido(BigDecimal valorLiquido) { this.valorLiquido = valorLiquido; }

    public String getPagamento() { return pagamento; }
    public void setPagamento(String pagamento) { this.pagamento = pagamento; }

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }

    public String getParcela() { return parcela; }
    public void setParcela(String parcela) { this.parcela = parcela; }

    public String getQuantidadeParcelas() { return quantidadeParcelas; }
    public void setQuantidadeParcelas(String quantidadeParcelas) { this.quantidadeParcelas = quantidadeParcelas; }

    public LocalDate getDataPrevistaPagamento() { return dataPrevistaPagamento; }
    public void setDataPrevistaPagamento(LocalDate dataPrevistaPagamento) { this.dataPrevistaPagamento = dataPrevistaPagamento; }

    public BigDecimal getTaxaParcelamentoComprador() { return taxaParcelamentoComprador; }
    public void setTaxaParcelamentoComprador(BigDecimal taxaParcelamentoComprador) { this.taxaParcelamentoComprador = taxaParcelamentoComprador; }

    public BigDecimal getTarifaBoletoComprador() { return tarifaBoletoComprador; }
    public void setTarifaBoletoComprador(BigDecimal tarifaBoletoComprador) { this.tarifaBoletoComprador = tarifaBoletoComprador; }

    public BigDecimal getValorOriginalTransacao() { return valorOriginalTransacao; }
    public void setValorOriginalTransacao(BigDecimal valorOriginalTransacao) { this.valorOriginalTransacao = valorOriginalTransacao; }

    public BigDecimal getTaxaParcelamentoVendedor() { return taxaParcelamentoVendedor; }
    public void setTaxaParcelamentoVendedor(BigDecimal taxaParcelamentoVendedor) { this.taxaParcelamentoVendedor = taxaParcelamentoVendedor; }

    public BigDecimal getTaxaIntermediacao() { return taxaIntermediacao; }
    public void setTaxaIntermediacao(BigDecimal taxaIntermediacao) { this.taxaIntermediacao = taxaIntermediacao; }

    public BigDecimal getTarifaBoletoVendedor() { return tarifaBoletoVendedor; }
    public void setTarifaBoletoVendedor(BigDecimal tarifaBoletoVendedor) { this.tarifaBoletoVendedor = tarifaBoletoVendedor; }

    public BigDecimal getRepasseAplicacao() { return repasseAplicacao; }
    public void setRepasseAplicacao(BigDecimal repasseAplicacao) { this.repasseAplicacao = repasseAplicacao; }

    public BigDecimal getValorLiquidoTransacao() { return valorLiquidoTransacao; }
    public void setValorLiquidoTransacao(BigDecimal valorLiquidoTransacao) { this.valorLiquidoTransacao = valorLiquidoTransacao; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    public String getFiller() { return filler; }
    public void setFiller(String filler) { this.filler = filler; }

    public String getMeioPagamento() { return meioPagamento; }
    public void setMeioPagamento(String meioPagamento) { this.meioPagamento = meioPagamento; }

    public String getInstituicaoFinanceira() { return instituicaoFinanceira; }
    public void setInstituicaoFinanceira(String instituicaoFinanceira) { this.instituicaoFinanceira = instituicaoFinanceira; }

}
