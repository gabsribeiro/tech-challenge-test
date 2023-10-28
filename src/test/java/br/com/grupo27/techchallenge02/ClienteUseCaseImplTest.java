package br.com.grupo27.techchallenge02;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import br.com.grupo27.techchallenge02.adapters.gateways.ClienteGateway;
import br.com.grupo27.techchallenge02.application.dto.ClienteDTO;
import br.com.grupo27.techchallenge02.application.usecases.ClienteUseCaseImpl;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.ClienteUseCase;
import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.valuesObjects.ValidadorCPF;

import java.util.Collections;
import java.util.List;

public class ClienteUseCaseImplTest {

    private ClienteGateway clienteRepository;
    private ClienteUseCase clienteUseCase;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteGateway.class);
        clienteUseCase = new ClienteUseCaseImpl(clienteRepository);
    }

    @Test
    void deveCriarNovoCliente() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, new ValidadorCPF("25277560009"), "Fulano de Tal", "fulano@example.com");
        
        when(clienteRepository.findByCpf(ArgumentMatchers.any())).thenReturn(null);
        when(clienteRepository.saveCliente(ArgumentMatchers.any())).thenReturn(new Cliente());

        ClienteDTO createdCliente = clienteUseCase.createCliente(clienteDTO);

        assertNotNull(createdCliente);
        
    }

    @Test
    void deveAtualizarCliente() {
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO(1L, new ValidadorCPF("25277560009"), "Fulano de Tal", "fulano@example.com");

        when(clienteRepository.findByCpf(ArgumentMatchers.any())).thenReturn(null);
        when(clienteRepository.updateCliente(ArgumentMatchers.eq(id), ArgumentMatchers.any())).thenReturn(new Cliente()); 

        ClienteDTO updatedCliente = clienteUseCase.updateCliente(id, clienteDTO);

        assertNotNull(updatedCliente);
        
    }

    @Test
    void deveBuscarClientePorId() {
        Long id = 1L;
        when(clienteRepository.findById(ArgumentMatchers.eq(id))).thenReturn(new Cliente());

        ClienteDTO foundCliente = clienteUseCase.getClienteById(id);

        assertNotNull(foundCliente);
        
    }

    @Test
    void deveExcluirCliente() {
        Long id = 1L;
        when(clienteRepository.deleteCliente(ArgumentMatchers.eq(id))).thenReturn(true); 

        boolean isDeleted = clienteUseCase.deleteCliente(id);

        assertTrue(isDeleted);
    }

    @Test
    void deveBuscarTodosOsClientes() {
        when(clienteRepository.listAllClientes()).thenReturn(Collections.singletonList(new Cliente())); 
        List<ClienteDTO> clientes = clienteUseCase.getAllClientes();

        assertFalse(clientes.isEmpty());
        
    }

    @Test
    void deveBuscarClientePorCPF() {
        String cpf = "25277560009"; // peguei 4devs.com.br

        when(clienteRepository.findByCpf(ArgumentMatchers.any())).thenReturn(new Cliente()); 

        ClienteDTO foundCliente = clienteUseCase.getClienteByCPF(cpf);

        assertNotNull(foundCliente);
        
    }
}