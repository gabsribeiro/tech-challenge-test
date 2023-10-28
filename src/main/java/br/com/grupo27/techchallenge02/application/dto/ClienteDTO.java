package br.com.grupo27.techchallenge02.application.dto;

import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.valuesObjects.ValidadorCPF;

public record ClienteDTO(Long id, ValidadorCPF cpf, String nome, String email) {
    public Cliente toCliente() {
        return new Cliente(this.id, this.cpf, this.nome, this.email);
    }
}