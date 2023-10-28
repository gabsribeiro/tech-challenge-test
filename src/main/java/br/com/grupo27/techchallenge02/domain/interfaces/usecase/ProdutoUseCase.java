package br.com.grupo27.techchallenge02.domain.interfaces.usecase;

import java.util.List;

import br.com.grupo27.techchallenge02.application.dto.ProdutoDTO;
import br.com.grupo27.techchallenge02.domain.enums.Categoria;

public interface ProdutoUseCase {

    ProdutoDTO getProdutoById(Long id);

    ProdutoDTO createProduto(ProdutoDTO produtoDTO);

    ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO);

    boolean deleteProduto(Long id);
    List<ProdutoDTO> getAllProdutos();

    List<ProdutoDTO> getProdutosByCategoria(Categoria categoria);
    
}
