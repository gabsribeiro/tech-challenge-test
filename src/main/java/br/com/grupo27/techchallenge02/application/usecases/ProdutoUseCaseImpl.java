package br.com.grupo27.techchallenge02.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import br.com.grupo27.techchallenge02.adapters.gateways.ProdutoGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.ProdutoMapper;
import br.com.grupo27.techchallenge02.application.dto.ProdutoDTO;
import br.com.grupo27.techchallenge02.domain.enums.Categoria;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.ProdutoUseCase;
import br.com.grupo27.techchallenge02.domain.model.Produto;

public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoUseCaseImpl(ProdutoGateway produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoDTO createProduto(ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toProduto();
        produto = produtoRepository.saveProduto(produto);
        return produtoMapper.domainToDto(produto);
    }

    @Override
    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toProduto();
        produto = produtoRepository.updateProduto(id, produto);
        return produto != null ? produtoMapper.domainToDto(produto) : null;
    }

    @Override
    public ProdutoDTO getProdutoById(Long id) {
        Produto produto = produtoRepository.findProdutoById(id);
        return produto != null ? produtoMapper.domainToDto(produto) : null;
    }

    @Override
    public boolean deleteProduto(Long id) {
        return produtoRepository.deleteProduto(id);
    }

    @Override
    public List<ProdutoDTO> getAllProdutos() {
        return produtoRepository.listAllProdutos().stream()
                .map(produtoMapper::domainToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> getProdutosByCategoria(Categoria categoria) {
        return produtoRepository.listByCategoria(categoria).stream()
                .map(produtoMapper::domainToDto)
                .collect(Collectors.toList());
    }
}