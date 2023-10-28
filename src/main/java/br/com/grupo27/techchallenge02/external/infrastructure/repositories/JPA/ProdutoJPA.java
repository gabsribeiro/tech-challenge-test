package br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.grupo27.techchallenge02.domain.enums.Categoria;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.ProdutoEntity;

public interface ProdutoJPA extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);

}
