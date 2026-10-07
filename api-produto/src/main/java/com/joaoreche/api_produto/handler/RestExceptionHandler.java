package com.joaoreche.api_produto.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joaoreche.api_produto.exception.ResourceNotFoundException;
import com.joaoreche.api_produto.model.error.ErrorMessage;

/**
 * Tratador global de exceções da aplicação.
 *
 * Centraliza o tratamento de erros de todos os controllers, garantindo respostas
 * padronizadas em JSON ao cliente em situações de erro.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Trata exceções do tipo ResourceNotFoundException.
     *
     * Lançada quando um recurso solicitado (ex: produto por id) não existe no banco.
     *
     * @param ex exceção capturada com a mensagem de erro
     * @return {@code 404 Not Found} com o corpo de erro padronizado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorMessage error = new ErrorMessage("Recurso não encontrado", ex.getMessage(),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
