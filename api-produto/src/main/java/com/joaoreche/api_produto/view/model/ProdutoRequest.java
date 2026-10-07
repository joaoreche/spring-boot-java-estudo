package com.joaoreche.api_produto.view.model;

/**
 * DTO que representa os dados de entrada recebidos pelo cliente para
 * criação ou atualização de um produto.
 *
 * Não contém o campo id, pois este é gerado automaticamente
 * pelo banco de dados no momento do cadastro.
 */
public class ProdutoRequest {

    // #region Atributos

    private String nome;
    private String observacao;
    private Double valor;
    private Integer quantidade;

    // #endregion

    // #region Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    // #endregion
}
