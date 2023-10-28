package br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.grupo27.techchallenge02.domain.valuesObjects.ValidadorCPF;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteJPA extends JpaRepository<ClienteEntity, Long> {
    @Query(value = "SELECT * FROM clientes c WHERE c.name LIKE %:name%", nativeQuery = true)
    List<ClienteEntity> findByName(@Param("name") String name);

    Optional<ClienteEntity> findByCpf(ValidadorCPF cpf);
}
