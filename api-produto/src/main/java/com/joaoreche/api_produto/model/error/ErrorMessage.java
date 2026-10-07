package com.joaoreche.api_produto.model.error;

/**
 * Representa o modelo padronizado de resposta de erro da API.
 */
public class ErrorMessage {

    private final String titulo;
    private final String mensagem;
    private final Integer status;

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
