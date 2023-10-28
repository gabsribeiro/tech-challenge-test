package br.com.grupo27.techchallenge02.adapters.gateways;

import java.util.List;

import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.valuesObjects.ValidadorCPF;

public interface ClienteGateway {
    Cliente saveCliente(Cliente clienteDTO);
    Cliente updateCliente(Long id, Cliente clienteDTO);
    Cliente findById(Long id);
    boolean deleteCliente(Long id);
    List<Cliente> listAllClientes();
    Cliente findByCpf(ValidadorCPF cpf);
}