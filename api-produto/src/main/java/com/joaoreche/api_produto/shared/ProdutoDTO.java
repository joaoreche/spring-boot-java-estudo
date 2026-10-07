package com.joaoreche.api_produto.shared;

/**
 * Objeto de Transferência de Dados (DTO) utilizado internamente para
 * comunicação entre as camadas de Controller e Service.
 *
 * Desacopla a entidade de domínio Produto da camada de apresentação, permitindo
 * que cada camada evolua independentemente sem impactar as demais.
 */
public class ProdutoDTO {

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
