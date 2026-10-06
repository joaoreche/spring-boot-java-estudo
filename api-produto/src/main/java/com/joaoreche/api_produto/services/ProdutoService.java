package com.joaoreche.api_produto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.joaoreche.api_produto.model.Produto;
import com.joaoreche.api_produto.repository.ProdutoRepository;

// indica para o spring que isso é um service e passa a gerenciar a classe (injeção de dependência)
@Service
public class ProdutoService {

    /**
     * Injeção de dependência via construtor
     * 
     * Ao declarar o repositório como 'private final', garantimos que a dependência
     * seja imutável e obrigatoriamente fornecida no momento em que esta classe é
     * criada
     * 
     * O Spring detecta automaticamente este construtor único e injeta a instância
     * de 'ProdutoRepository' sem a necessidade da anotação @Autowired
     */
    private final ProdutoRepository produtoRepository;

    ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Método que retorna todos os produtos da lista
     * 
     * @return lista de produtos
     */
    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    /**
     * Método que retorna o produto encontrado pelo seu id
     * 
     * @param id do produto que será localizado
     * @return um produto caso ele seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Método para adicionar um produto na lista
     * 
     * @param produto que será adicionado
     * @return o produto que foi adicionado
     */
    public Produto addProduto(Produto produto) {
        return produtoRepository.addProduto(produto);
    }

    /**
     * Método para deletar um produto por id
     * 
     * @param id do produto a ser deletado
     */
    public void deleteProduto(Integer id) {
        produtoRepository.deleteProduto(id);
    }

    /**
     * Método para atualizar um produto na lista
     * 
     * @param produto que será atualizado
     * @return produto após atualizar a lista
     */
    public Produto updateProduto(Integer id, Produto produto) {

        produto.setId(id);

        return produtoRepository.updateProduto(produto);

    }
}
