package com.joaoreche.api_produto.view.model;

/**
 * DTO que representa os dados retornados pela API ao cliente após operações com produtos.
 */
public class ProdutoResponse {

    // #region Atributos

    private Integer id;
    private String nome;
    private String observacao;
    private Double valor;
    private Integer quantidade;

    // #endregion

    // #region Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
