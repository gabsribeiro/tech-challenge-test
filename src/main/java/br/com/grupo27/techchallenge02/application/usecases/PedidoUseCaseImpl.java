package br.com.grupo27.techchallenge02.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.grupo27.techchallenge02.adapters.gateways.ClienteGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.PedidoMapper;
import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;
import br.com.grupo27.techchallenge02.domain.enums.StatusPedido;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PagamentoUsecase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PedidoUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.model.Pedido;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.PedidoGatewayImpl;

public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoGatewayImpl pedidoGateway;
    private final PedidoMapper pedidoMapper;
    private final ClienteGateway clienteGateway;
    private final PagamentoUsecase pagamento;
    
    private static final Logger logger = LoggerFactory.getLogger(PedidoUseCaseImpl.class);

    

    public PedidoUseCaseImpl(PedidoGatewayImpl pedidoGateway, PedidoMapper pedidoMapper, ClienteGateway clienteGateway,
            PagamentoUsecase pagamento) {
        this.pedidoGateway = pedidoGateway;
        this.pedidoMapper = pedidoMapper;
        this.clienteGateway = clienteGateway;
        this.pagamento = pagamento;
    }

    @Override
    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoGateway.findPedidoById(id);
        return pedidoMapper.domainToDto(pedido);
    }

    @Override
    public List<PedidoDTO> getAllPedidos() {
        List<Pedido> pedidos = pedidoGateway.findAllPedidos();
        return pedidos.stream().map(pedidoMapper::domainToDto).collect(Collectors.toList());
    }

    @Override
    public PedidoDTO createPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteGateway.findById(pedidoDTO.idCliente());
        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);
        Pedido createdPedido = pedidoGateway.createPedido(pedido);
        PedidoDTO pedidoSalvo = pedidoMapper.domainToDto(createdPedido);
        pagamento.gerarCobranca(pedidoSalvo, cliente);
        return pedidoSalvo;
    }

    @Override
    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.dtoToDomain(pedidoDTO);
        Pedido updatedPedido = pedidoGateway.updatePedido(id, pedido);
        return pedidoMapper.domainToDto(updatedPedido);
    }

    @Override
    public boolean deletePedido(Long id) {
        return pedidoGateway.deletePedido(id);
    }
    
    @Override
    public List<Pedido> findPedidosByStatus(StatusPedido status) {
        return pedidoGateway.findPedidosByStatus(status);
    }

    @Override
    public List<PedidoDTO> findPedidosAtivos(){
        List<Pedido> pedidos = pedidoGateway.findPedidosAtivos();
        return pedidos.stream().map(pedidoMapper::domainToDto).collect(Collectors.toList());
    }

}
