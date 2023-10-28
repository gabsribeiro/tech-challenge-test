package br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.PagamentoEntity;


public interface PagamentoJPA extends JpaRepository<PagamentoEntity, Long> {

    @Query("SELECT p FROM PagamentoEntity p WHERE p.idPedido = :idPedido")
    PagamentoEntity findPagamentoByIdPedido(@Param("idPedido") Long idPedido);

}
