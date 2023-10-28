package br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.grupo27.techchallenge02.domain.enums.StatusPedido;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.PedidoEntity;

public interface PedidoJPA extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByPago(boolean pago);

    List<PedidoEntity> findByStatus(StatusPedido status);

    @Query("SELECT p FROM PedidoEntity p WHERE p.status NOT IN ('FINALIZADO', 'PENDENTE') ORDER BY p.dataCadastro DESC, CASE p.status WHEN 'PRONTO' THEN 1 WHEN 'EM_PREPARACAO' THEN 2 WHEN 'RECEBIDO' THEN 3 ELSE 4 END")
    List<PedidoEntity> findPedidosAtivos();
    

}
