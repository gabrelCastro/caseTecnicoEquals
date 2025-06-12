package com.gabriel.equalscase.model.visamaster;

import com.gabriel.equalscase.model.base.Detalhe;

/**
 * Representa um registro de detalhe específico da bandeira Master/Visa.
 * 
 * Essa classe estende {@link Detalhe} adicionando campos exclusivos dessa bandeira,
 * que incluem informações sobre o canal de entrada, dados do cartão, códigos de
 * autorização e identificadores adicionais.
 * 
 * Essa especialização permite que o processamento e o mapeamento dos arquivos
 * respeitem os campos específicos que só aparecem nos layouts de Master/Visa.
 */
public class DetalheMasterVisa extends Detalhe {

    /** Canal de entrada da transação (ex: TEF, PDV, e-commerce) */
    private String canalEntrada;

    /** Tipo ou identificação do leitor físico utilizado */
    private String leitor;

    /** Meio de captura da transação (ex: chip, tarja, NFC) */
    private String meioCaptura;

    /** Número lógico da transação, utilizado para controle e rastreamento */
    private String numeroLogico;

    /** Número sequencial único (NSU) da transação */
    private String nsu;

    /** Espaço reservado para layout fixo ou padding */
    private String filler2;

    /** BIN do cartão (primeiros dígitos do número) */
    private String cartaoBin;

    /** Identificador do portador do cartão (titular) */
    private String cartaoHolder;

    /** Código de autorização da transação, fornecido pela adquirente */
    private String codigoAutorizacao;

    /** Código CV (Comprovante de Venda), utilizado na conciliação */
    private String codigoCv;

    /** Espaço reservado para expansão futura ou padding */
    private String reservado;

    // Getters e Setters

    public String getCanalEntrada() { return canalEntrada; }
    public void setCanalEntrada(String canalEntrada) { this.canalEntrada = canalEntrada; }

    public String getLeitor() { return leitor; }
    public void setLeitor(String leitor) { this.leitor = leitor; }

    public String getMeioCaptura() { return meioCaptura; }
    public void setMeioCaptura(String meioCaptura) { this.meioCaptura = meioCaptura; }

    public String getNumeroLogico() { return numeroLogico; }
    public void setNumeroLogico(String numeroLogico) { this.numeroLogico = numeroLogico; }

    public String getNsu() { return nsu; }
    public void setNsu(String nsu) { this.nsu = nsu; }

    public String getFiller2() { return filler2; }
    public void setFiller2(String filler2) { this.filler2 = filler2; }

    public String getCartaoBin() { return cartaoBin; }
    public void setCartaoBin(String cartaoBin) { this.cartaoBin = cartaoBin; }

    public String getCartaoHolder() { return cartaoHolder; }
    public void setCartaoHolder(String cartaoHolder) { this.cartaoHolder = cartaoHolder; }

    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public void setCodigoAutorizacao(String codigoAutorizacao) { this.codigoAutorizacao = codigoAutorizacao; }

    public String getCodigoCv() { return codigoCv; }
    public void setCodigoCv(String codigoCv) { this.codigoCv = codigoCv; }

    public String getReservado() { return reservado; }
    public void setReservado(String reservado) { this.reservado = reservado; }
}
