package com.joaoreche.api_produto.model.error;

public class ErrorMessage {

    private String titulo, mensagem;
    private Integer status;

    public ErrorMessage(String titulo, String mensagem, Integer status) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Integer getStatus() {
        return status;
    }

}
