package com.joaoreche.api_produto.view.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoreche.api_produto.services.ProdutoService;
import com.joaoreche.api_produto.shared.ProdutoDTO;
import com.joaoreche.api_produto.view.model.ProdutoRequest;
import com.joaoreche.api_produto.view.model.ProdutoResponse;

/**
 * Controller responsável por expor os endpoints REST da API de Produtos.
 *
 * Recebe as requisições HTTP, delega o processamento ao ProdutoService
 * e retorna as respostas devidamente mapeadas para ProdutoResponse.
 */
// Indica ao Spring que essa classe é um Controller REST, gerenciando seu ciclo de vida (injeção de dependência)
@RestController
// Define a rota base que este controller irá "ouvir"
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ModelMapper mapper;

    public ProdutoController(ProdutoService produtoService, ModelMapper mapper) {
        this.produtoService = produtoService;
        this.mapper = mapper;
    }

    /**
     * Retorna todos os produtos cadastrados.
     *
     * @return {@code 200 OK} com a lista de produtos
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {

        // Obtém a lista de ProdutoDTOs retornada pelo Service
        List<ProdutoDTO> produtosDto = produtoService.obterTodos();

        // Converte cada ProdutoDTO para ProdutoResponse antes de retornar ao cliente
        List<ProdutoResponse> resposta = produtosDto.stream()
                .map(produto -> mapper.map(produto, ProdutoResponse.class))
                .toList();

        return ResponseEntity.ok(resposta);
    }

    /**
     * Busca um produto pelo seu identificador único.
     *
     * PathVariable extrai o valor do segmento {id} da URL
     * e o vincula ao parâmetro do método, permitindo identificar o recurso.
     *
     * @param id identificador do produto
     * @return {@code 200 OK} com os dados do produto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable Integer id) {

        // O Service lança ResourceNotFoundException automaticamente se o produto não existir
        ProdutoDTO dto = produtoService.obterPorId(id);

        // Converte o ProdutoDTO retornado para ProdutoResponse
        ProdutoResponse resposta = mapper.map(dto, ProdutoResponse.class);

        return ResponseEntity.ok(resposta);
    }

    /**
     * Cadastra um novo produto.
     *
     * RequestBody captura o corpo da requisição HTTP e o converte
     * automaticamente em um objeto ProdutoRequest.
     *
     * @param produtoReq dados do produto a ser criado
     * @return {@code 201 Created} com os dados do produto cadastrado
     */
    @PostMapping
    public ResponseEntity<ProdutoResponse> addProduto(@RequestBody ProdutoRequest produtoReq) {

        // Converte o ProdutoRequest recebido para ProdutoDTO (formato interno entre camadas)
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

        // Envia o DTO para o Service realizar o cadastro no banco de dados
        produtoDto = produtoService.addProduto(produtoDto);

        // Converte o ProdutoDTO (com id gerado) para ProdutoResponse e retorna ao cliente
        ProdutoResponse resposta = mapper.map(produtoDto, ProdutoResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    /**
     * Remove um produto pelo seu identificador único.
     *
     * @param id identificador do produto a ser removido
     * @return {@code 204 No Content} em caso de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {

        produtoService.deleteProduto(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param id         identificador do produto a ser atualizado
     * @param produtoReq novos dados do produto
     * @return {@code 200 OK} com os dados atualizados do produto
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(@PathVariable Integer id,
            @RequestBody ProdutoRequest produtoReq) {

        // Converte o ProdutoRequest recebido para ProdutoDTO (formato interno entre camadas)
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

        // Envia o DTO para o Service realizar a atualização no banco de dados
        produtoDto = produtoService.updateProduto(id, produtoDto);

        // Converte o ProdutoDTO atualizado para ProdutoResponse e retorna ao cliente
        ProdutoResponse resposta = mapper.map(produtoDto, ProdutoResponse.class);

        return ResponseEntity.ok(resposta);
    }
}
