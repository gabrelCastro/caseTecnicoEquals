package com.gabriel.equalscase.model.base;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe base que representa um registro de detalhe de uma transação.
 * 
 * Essa classe serve como superclasse para tipos específicos de detalhe, como
 * {@code DetalheMasterVisa}, e contém todos os campos comuns usados no processamento
 * e análise dos dados de vendas.
 * 
 * Os dados representam informações transacionais detalhadas como valores, datas, horários,
 * status de pagamento, formas de pagamento, e taxas.
 */
public class Detalhe {

    /** Identificador único do detalhe (gerado pelo banco) */
    private Integer id;

    /** Tipo do registro, geralmente '1' para linhas de detalhe */
    private String tipoRegistro;

    /** Código do estabelecimento onde a transação ocorreu */
    private String estabelecimento;

    /** Data da transação */
    private LocalDate dataTransacao;

    /** Data do evento relacionado à transação */
    private LocalDate dataEvento;

    /** Hora do evento (ex: hora da captura ou confirmação) */
    private LocalTime horaEvento;

    /** Tipo de evento da transação (ex: autorização, cancelamento) */
    private String tipoEvento;

    /** Tipo da transação (ex: crédito, débito, etc.) */
    private String tipoTransacao;

    /** Número de série do leitor utilizado (quando aplicável) */
    private String numeroSerieLeitor;

    /** Código único da transação no sistema */
    private String codigoTransacao;

    /** Código do pedido associado à transação */
    private String codigoPedido;

    /** Valor total da transação */
    private BigDecimal valorTotal;

    /** Valor líquido recebido após descontos */
    private BigDecimal valorLiquido;

    /** Forma de pagamento utilizada (ex: cartão, boleto) */
    private String pagamento;

    /** Plano de pagamento (ex: parcelado, à vista) */
    private String plano;

    /** Número da parcela atual (ex: 1 de 3) */
    private String parcela;

    /** Quantidade total de parcelas */
    private String quantidadeParcelas;

    /** Data prevista para o pagamento ao vendedor */
    private LocalDate dataPrevistaPagamento;

    /** Taxa paga pelo comprador em transações parceladas */
    private BigDecimal taxaParcelamentoComprador;

    /** Tarifa do boleto paga pelo comprador (se aplicável) */
    private BigDecimal tarifaBoletoComprador;

    /** Valor original da transação antes de taxas/descontos */
    private BigDecimal valorOriginalTransacao;

    /** Taxa paga pelo vendedor em parcelamentos */
    private BigDecimal taxaParcelamentoVendedor;

    /** Taxa de intermediação da transação (ex: comissão da plataforma) */
    private BigDecimal taxaIntermediacao;

    /** Tarifa de boleto cobrada do vendedor (se houver) */
    private BigDecimal tarifaBoletoVendedor;

    /** Valor de repasse de alguma aplicação financeira associada */
    private BigDecimal repasseAplicacao;

    /** Valor líquido final após todas as deduções */
    private BigDecimal valorLiquidoTransacao;

    /** Status atual do pagamento (ex: concluído, pendente, cancelado) */
    private String statusPagamento;

    /** Espaço reservado ou ignorado (usado para alinhamento de layout fixo) */
    private String filler;

    /** Meio de pagamento utilizado (ex: máquina TEF, app, site) */
    private String meioPagamento;

    /** Instituição financeira que processou a transação */
    private String instituicaoFinanceira;

    // Getters e setters abaixo...

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public String getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }

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
