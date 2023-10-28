package br.com.grupo27.techchallenge02.adapters.mappers;

import org.springframework.stereotype.Component;

import br.com.grupo27.techchallenge02.application.dto.ProdutoDTO;
import br.com.grupo27.techchallenge02.domain.model.Produto;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.ProdutoEntity;

@Component
public class ProdutoMapper {

    public ProdutoEntity domainToEntity(Produto produto) {
        return new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria()
        );
    }

    public Produto dtoToDomain(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.descricao(),
                produtoDTO.preco(),
                produtoDTO.categoria()
        );
    }

    public ProdutoDTO domainToDto(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria()
        );
    }

    public Produto entityToDomain(ProdutoEntity produtoEntity) {
        return new Produto(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getPreco(),
                produtoEntity.getCategoria()
        );
    }
}
