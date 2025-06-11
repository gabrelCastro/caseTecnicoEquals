package com.gabriel.equalscase.model.visamaster;

import com.gabriel.equalscase.model.base.Detalhe;

public class DetalheMasterVisa extends Detalhe{
    
    private String canalEntrada;
    private String leitor;
    private String meioCaptura;
    private String numeroLogico;
    private String nsu;
    private String filler2;
    private String cartaoBin;
    private String cartaoHolder;
    private String codigoAutorizacao;
    private String codigoCv;
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
