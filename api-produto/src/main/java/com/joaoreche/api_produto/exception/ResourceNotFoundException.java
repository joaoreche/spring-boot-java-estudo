package com.joaoreche.api_produto.exception;

/**
 * Exceção lançada quando um recurso solicitado não é encontrado no banco de dados.
 *
 * <p>Ao ser capturada pelo {@link com.joaoreche.api_produto.handler.RestExceptionHandler},
 * resulta em uma resposta HTTP {@code 404 Not Found} com uma mensagem de erro padronizada.</p>
 *
 * <p>Estende {@link RuntimeException} para que não seja necessário declará-la
 * explicitamente nas assinaturas dos métodos (unchecked exception).</p>
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
