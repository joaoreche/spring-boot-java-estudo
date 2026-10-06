package com.joaoreche.api_produto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoreche.api_produto.model.Produto;
import com.joaoreche.api_produto.services.ProdutoService;

// indica para o spring que isso é um controller e passa a gerenciar a classe (injeção de dependência)
@RestController
// mapeando a rota que o controller vai "ouvir"
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> obterTodos() {
        return produtoService.obterTodos();
    }

    /*
     * @PathVariable: Extrai um valor enviado diretamente na URL da requisição
     * e o vincula ao parâmetro do método (o 'id'), permitindo identificar um
     * recurso específico.
     */
    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable Integer id) {
        return produtoService.obterPorId(id);
    }

    /*
     * @RequestBody: Captura o corpo da requisição HTTP e o converte automaticamente
     * em um objeto Java do tipo Produto.
     */
    @PostMapping
    public Produto addProduto(@RequestBody Produto produto) {
        return produtoService.addProduto(produto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduto(@PathVariable Integer id) {
        produtoService.deleteProduto(id);
        return "Produto com o id: " + id + " foi deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.updateProduto(id, produto);
    }
}
