package com.joaoreche.api_produto.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.joaoreche.api_produto.exception.ResourceNotFoundException;
import com.joaoreche.api_produto.model.Produto;

import org.springframework.stereotype.Repository;

// indica para o spring que isso é um repository e passa a gerenciar a classe (injeção de dependência)
@Repository
public class ProdutoRepository {

    // Simulando Banco de Dados
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método que retorna todos os produtos da lista
     * 
     * @return lista de produtos
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método que retorna o produto encontrado pelo seu id
     * 
     * @param id do produto que será localizado
     * @return um produto caso ele seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos.stream().filter(produto -> produto.getId().equals(id)).findFirst();
    }

    /**
     * Método para adicionar um produto na lista
     * 
     * @param produto que será adicionado
     * @return o produto que foi adicionado
     */
    public Produto addProduto(Produto produto) {

        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Método para deletar um produto por id
     * 
     * @param id do produto a ser deletado
     */
    public void deleteProduto(Integer id) {
        
        boolean removido = produtos.removeIf(produto -> produto.getId().equals(id));

        if(!removido) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    /**
     * Método para atualizar um produto na lista
     * 
     * @param produto que será atualizado
     * @return produto após atualizar a lista
     */
    public Produto updateProduto(Produto produto) {

        // Encontrar produto na lista
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoEncontrado.get().setNome(produto.getNome());
        produtoEncontrado.get().setObservacao(produto.getObservacao());
        produtoEncontrado.get().setQuantidade(produto.getQuantidade());
        produtoEncontrado.get().setValor(produto.getValor());

        return produto;
    }
}
