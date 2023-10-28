package br.com.grupo27.techchallenge02.external.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge02.adapters.gateways.PedidoGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.PedidoMapper;
import br.com.grupo27.techchallenge02.domain.enums.StatusPedido;
import br.com.grupo27.techchallenge02.domain.model.Pedido;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.PedidoEntity;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.PedidoJPA;

@Repository
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoJPA pedidoJPA;
    private final PedidoMapper pedidoMapper;

    public PedidoGatewayImpl(PedidoJPA pedidoJPA, PedidoMapper pedidoMapper) {
        this.pedidoJPA = pedidoJPA;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Pedido findPedidoById(Long id) {
        return pedidoJPA.findById(id)
                .map(pedidoMapper::entityToDomain)
                .orElse(null);
    }

    @Override
    public List<Pedido> findAllPedidos() {
        List<PedidoEntity> pedidoEntities = pedidoJPA.findAll();
        return pedidoEntities.stream()
                .map(pedidoMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.domainToEntity(pedido);
        pedidoEntity.setDataCadastro(new Date());
        PedidoEntity savedPedidoEntity = pedidoJPA.save(pedidoEntity);
        return pedidoMapper.entityToDomain(savedPedidoEntity);
    }

    @Override
    public Pedido updatePedido(Long id, Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.domainToEntity(pedido);
        pedidoEntity.setDataAlteracao(new Date());
        PedidoEntity savedPedidoEntity = pedidoJPA.save(pedidoEntity);
        return pedidoMapper.entityToDomain(savedPedidoEntity);
    }

    @Override
    public boolean deletePedido(Long id) {
        pedidoJPA.deleteById(id);
        return true;
    }

    @Override
    public List<Pedido> findPedidosByStatusPagamento(boolean pago) {
        List<PedidoEntity> pedidoEntities = pedidoJPA.findByPago(pago);
        return pedidoEntities.stream()
            .map(pedidoMapper::entityToDomain)
            .collect(Collectors.toList());
    } 

    @Override
    public List<Pedido> findPedidosByStatus(StatusPedido status) {
        List<PedidoEntity> pedidoEntities = pedidoJPA.findByStatus(status);
        return pedidoEntities.stream()
            .map(pedidoMapper::entityToDomain)
            .collect(Collectors.toList());
    }

    public List<Pedido> findPedidosAtivos() {
        List<PedidoEntity> pedidoEntities = pedidoJPA.findPedidosAtivos();
        return pedidoEntities.stream()
                .map(pedidoMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
