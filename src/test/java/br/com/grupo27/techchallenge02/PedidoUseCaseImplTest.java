package br.com.grupo27.techchallenge02;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import br.com.grupo27.techchallenge02.adapters.gateways.ClienteGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.PedidoMapper;
import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;

import br.com.grupo27.techchallenge02.application.usecases.PedidoUseCaseImpl;
import br.com.grupo27.techchallenge02.domain.enums.StatusPedido;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PagamentoUsecase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PedidoUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge02.domain.model.Pedido;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.PedidoGatewayImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class PedidoUseCaseImplTest {

    private PedidoGatewayImpl pedidoAdapter;
    private PedidoMapper pedidoMapper;
    private PixUseCase pagamentosClient;
    private PedidoUseCase pedidoUseCase;
    private ClienteGateway clienteGateway;
    private PagamentoUsecase pagamentoUsecase;

    @BeforeEach
    void setUp() {
        pedidoAdapter = mock(PedidoGatewayImpl.class);
        pedidoMapper = mock(PedidoMapper.class);
        clienteGateway = mock(ClienteGateway.class);
        pagamentoUsecase = mock(PagamentoUsecase.class);
        pedidoUseCase = new PedidoUseCaseImpl(pedidoAdapter, pedidoMapper, clienteGateway, pagamentoUsecase);
    }

    @Test
    void deveBuscarPedidoPorId() {
        Long id = 1L;
        PedidoDTO pedidoDTO = new PedidoDTO(id, 1L, Collections.emptyList(), BigDecimal.valueOf(100.0), StatusPedido.PENDENTE, false, null, null);

        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);
        when(pedidoAdapter.findPedidoById(ArgumentMatchers.eq(id))).thenReturn(pedido);
        when(pedidoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(pedidoDTO);

        PedidoDTO pedidoEncontrado = pedidoUseCase.getPedidoById(id);

        assertNotNull(pedidoEncontrado);
        assertEquals(pedidoDTO, pedidoEncontrado);
    }

    @Test
    void deveBuscarTodosOsPedidos() {
        PedidoDTO pedidoDTO = new PedidoDTO(1L, 1L, Collections.emptyList(), BigDecimal.valueOf(100.0), StatusPedido.PENDENTE, false, null, null);
        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);

        when(pedidoAdapter.findAllPedidos()).thenReturn(Collections.singletonList(pedido));
        when(pedidoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(pedidoDTO);

        List<PedidoDTO> pedidos = pedidoUseCase.getAllPedidos();

        assertFalse(pedidos.isEmpty());
        assertEquals(pedidoDTO, pedidos.get(0));
    }

    @Test
    void deveCriarNovoPedido() {
        PedidoDTO pedidoDTO = new PedidoDTO(1L, 1L, Collections.emptyList(), BigDecimal.valueOf(100.0), StatusPedido.PENDENTE, false, null, null);

        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);
        when(pedidoMapper.dtoToDomain(ArgumentMatchers.any())).thenReturn(pedido);
        when(pedidoAdapter.createPedido(ArgumentMatchers.any())).thenReturn(pedido);
        when(pedidoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(pedidoDTO);

        PedidoDTO novoPedido = pedidoUseCase.createPedido(pedidoDTO);

        assertNotNull(novoPedido);
        assertEquals(pedidoDTO, novoPedido);
    }

     @Test
    void deveBuscarPedidosPorStatus() {
        StatusPedido status = StatusPedido.PENDENTE;
        PedidoDTO pedidoDTO = new PedidoDTO(1L, 1L, Collections.emptyList(), BigDecimal.valueOf(100.0), StatusPedido.PENDENTE, false, null, null);
        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);

        when(pedidoAdapter.findPedidosByStatus(ArgumentMatchers.eq(status))).thenReturn(Collections.singletonList(pedido));

        List<Pedido> listaPedidos = pedidoUseCase.findPedidosByStatus(status);

        assertFalse(listaPedidos.isEmpty());
        assertEquals(pedido, listaPedidos.get(0));
    }
}





