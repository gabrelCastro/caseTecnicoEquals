package com.gabriel.equalscase.model;

public class Trailer {

    private Integer id;
    private String tipoRegistro;
    private Integer totalRegistro;
    private String reservado;

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public Integer getTotalRegistro() { return totalRegistro; }
    public void setTotalRegistro(Integer totalRegistro) { this.totalRegistro = totalRegistro; }

    public String getReservado() { return reservado; }
    public void setReservado(String reservado) { this.reservado = reservado; }
}
