package com.joaoreche.api_produto.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joaoreche.api_produto.exception.ResourceNotFoundException;
import com.joaoreche.api_produto.model.Produto;
import com.joaoreche.api_produto.repository.ProdutoRepository;
import com.joaoreche.api_produto.shared.ProdutoDTO;

/**
 * Camada de serviço responsável pelas regras de negócio relacionadas a produtos.
 *
 * Realiza a conversão entre a entidade Produto e o ProdutoDTO,
 * delegando as operações de persistência ao ProdutoRepository.
 */
// Indica ao Spring que essa classe é um Service, gerenciando seu ciclo de vida (injeção de dependência)
@Service
public class ProdutoService {

    /**
     * Injeção de dependência via construtor.
     *
     * O Spring detecta automaticamente este construtor e injeta as instâncias
     * sem necessidade da anotação @Autowired.
     */
    private final ProdutoRepository produtoRepository;
    private final ModelMapper mapper;

    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    /**
     * Retorna todos os produtos cadastrados no banco de dados.
     *
     * @return lista de DTOs com os dados de todos os produtos
     */
    public List<ProdutoDTO> obterTodos() {

        // Obtém a lista de entidades Produto do banco de dados
        List<Produto> produtos = produtoRepository.findAll();

        // Converte cada entidade Produto para ProdutoDTO antes de retornar ao Controller
        return produtos.stream()
                .map(produto -> mapper.map(produto, ProdutoDTO.class))
                .toList();
    }

    /**
     * Busca um produto pelo seu identificador único.
     *
     * @param id identificador do produto
     * @return DTO com os dados do produto encontrado
     * @throws ResourceNotFoundException se nenhum produto for encontrado com o id informado
     */
    public ProdutoDTO obterPorId(Integer id) {

        // Busca o produto no banco; lança exceção automaticamente se não encontrado
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto com id:" + id + " não foi encontrado"));

        // Converte a entidade Produto para ProdutoDTO e retorna
        return mapper.map(produto, ProdutoDTO.class);
    }

    /**
     * Cadastra um novo produto no banco de dados.
     *
     * @param produtoDto dados do produto a ser cadastrado
     * @return DTO com os dados do produto após o cadastro, incluindo o id gerado
     */
    public ProdutoDTO addProduto(ProdutoDTO produtoDto) {

        // Remove o id para garantir que a operação seja de cadastro (INSERT), não de atualização
        produtoDto.setId(null);

        // Converte o ProdutoDTO para a entidade Produto gerenciada pelo JPA
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Persiste o produto no banco e obtém a entidade salva (com o id gerado)
        Produto salvo = produtoRepository.save(produto);

        // Converte a entidade salva (com id) de volta para ProdutoDTO e retorna
        return mapper.map(salvo, ProdutoDTO.class);
    }

    /**
     * Remove um produto do banco de dados pelo seu identificador único.
     *
     * @param id identificador do produto a ser removido
     * @throws ResourceNotFoundException se nenhum produto for encontrado com o id informado
     */
    public void deleteProduto(Integer id) {

        // Verifica se o produto existe antes de tentar deletar
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Não foi possível deletar o produto com id:" + id + ". Produto não existe!");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um produto existente no banco de dados.
     *
     * @param id         identificador do produto a ser atualizado
     * @param produtoDto novos dados do produto
     * @return DTO com os dados do produto após a atualização
     * @throws ResourceNotFoundException se nenhum produto for encontrado com o id informado
     */
    public ProdutoDTO updateProduto(Integer id, ProdutoDTO produtoDto) {

        // Verifica se o produto existe antes de tentar atualizar
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Não foi possível atualizar o produto com id:" + id + ". Produto não existe!");
        }

        // Define o id no DTO para garantir que a operação seja de atualização (UPDATE)
        produtoDto.setId(id);

        // Converte o ProdutoDTO para a entidade Produto gerenciada pelo JPA
        Produto produtoConvertido = mapper.map(produtoDto, Produto.class);

        // Persiste as alterações no banco (o JPA identifica como UPDATE por conta do id definido)
        Produto salvo = produtoRepository.save(produtoConvertido);

        // Converte a entidade atualizada de volta para ProdutoDTO e retorna
        return mapper.map(salvo, ProdutoDTO.class);
    }
}
