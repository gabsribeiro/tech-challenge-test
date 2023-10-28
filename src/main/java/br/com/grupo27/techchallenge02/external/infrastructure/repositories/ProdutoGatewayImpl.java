package br.com.grupo27.techchallenge02.external.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge02.adapters.gateways.ProdutoGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.ProdutoMapper;
import br.com.grupo27.techchallenge02.domain.enums.Categoria;
import br.com.grupo27.techchallenge02.domain.model.Produto;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.ProdutoEntity;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.ProdutoJPA;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoJPA produtoRepositoryJPA;
    private final ProdutoMapper produtoMapper;

    public ProdutoGatewayImpl(ProdutoJPA produtoRepositoryJPA, ProdutoMapper produtoMapper) {
        this.produtoRepositoryJPA = produtoRepositoryJPA;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto saveProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepositoryJPA.save(produtoMapper.domainToEntity(produto));
        return produtoMapper.entityToDomain(produtoEntity);
    }

    @Override
    public Produto updateProduto(Long id, Produto produto) {
        return produtoRepositoryJPA.findById(id).map(produtoEntity -> {
            produtoEntity.setNome(produto.getNome());
            produtoEntity.setDescricao(produto.getDescricao());
            produtoEntity.setPreco(produto.getPreco());
            produtoEntity.setCategoria(produto.getCategoria());
            produtoEntity = produtoRepositoryJPA.save(produtoEntity);
            return produtoMapper.entityToDomain(produtoEntity);
        }).orElse(null);
    }

    @Override
    public Produto findProdutoById(Long id) {
        return produtoMapper.entityToDomain(produtoRepositoryJPA.findById(id).orElse(null));
    }

    @Override
    public boolean deleteProduto(Long id) {
        if (produtoRepositoryJPA.existsById(id)) {
            produtoRepositoryJPA.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Produto> listAllProdutos() {
        return produtoRepositoryJPA.findAll().stream()
                .map(produtoMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Produto> listByCategoria(Categoria categoria) {
                return produtoRepositoryJPA.findByCategoria(categoria).stream()
                .map(produtoMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
