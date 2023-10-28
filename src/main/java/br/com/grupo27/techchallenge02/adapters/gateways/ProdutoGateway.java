package br.com.grupo27.techchallenge02.adapters.gateways;

import java.util.List;

import br.com.grupo27.techchallenge02.domain.enums.Categoria;
import br.com.grupo27.techchallenge02.domain.model.Produto;

public interface ProdutoGateway {
    Produto saveProduto(Produto produto);
    Produto updateProduto(Long id, Produto produto);
    Produto findProdutoById(Long id);
    boolean deleteProduto(Long id);
    List<Produto> listAllProdutos();
    List<Produto> listByCategoria(Categoria categoria);
}
