package com.joaoreche.api_produto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de beans utilitários da aplicação.
 *
 * O ModelMapper é registrado como singleton gerenciado pelo Spring,
 * garantindo que uma única instância seja reutilizada em toda a aplicação.
 * Isso evita o custo de criação repetida do objeto, que realiza reflexão
 * internamente para registrar os mapeamentos.
 */
@Configuration
public class AppConfig {

    /**
     * Registra o ModelMapper como um Bean do Spring.
     *
     * Ao ser declarado aqui, o Spring injeta automaticamente essa instância
     * em qualquer classe que a declare via construtor.
     *
     * @return instância configurada do ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
