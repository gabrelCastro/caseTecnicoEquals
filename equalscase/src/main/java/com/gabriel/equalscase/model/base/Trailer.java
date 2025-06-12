package com.gabriel.equalscase.model.base;

/**
 * Classe base que representa o trailer de um arquivo de vendas.
 *
 * O trailer é geralmente a última linha do arquivo, contendo informações de
 * controle e validação, como o total de registros processados.
 * 
 * Essa classe pode ser estendida por versões específicas de bandeiras, como {@code TrailerMasterVisa}.
 */
public class Trailer {

    /** Identificador único do trailer no banco de dados */
    private Integer id;

    /** Tipo do registro — geralmente '9' para trailer */
    private String tipoRegistro;

    /** Total de registros de detalhe (tipo 1) presentes no arquivo */
    private Integer totalRegistro;

    /** Campo reservado (pode conter espaços ou dados futuros) */
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
