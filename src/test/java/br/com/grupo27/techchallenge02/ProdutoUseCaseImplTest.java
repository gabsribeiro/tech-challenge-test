package br.com.grupo27.techchallenge02;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import br.com.grupo27.techchallenge02.adapters.gateways.ProdutoGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.ProdutoMapper;
import br.com.grupo27.techchallenge02.application.dto.ProdutoDTO;
import br.com.grupo27.techchallenge02.application.usecases.ProdutoUseCaseImpl;
import br.com.grupo27.techchallenge02.domain.enums.Categoria;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.ProdutoUseCase;
import br.com.grupo27.techchallenge02.domain.model.Produto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProdutoUseCaseImplTest {

    private ProdutoGateway produtoRepository;
    private ProdutoMapper produtoMapper;
    private ProdutoUseCase produtoUseCase;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoGateway.class);
        produtoMapper = mock(ProdutoMapper.class);
        produtoUseCase = new ProdutoUseCaseImpl(produtoRepository, produtoMapper);
    }

    @Test
    void deveCriarNovoProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", BigDecimal.valueOf(10.0), Categoria.BEBIDA);

        Produto produto = produtoDTO.toProduto();
        when(produtoRepository.saveProduto(ArgumentMatchers.any())).thenReturn(produto);
        when(produtoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(produtoDTO);

        ProdutoDTO createdProduto = produtoUseCase.createProduto(produtoDTO);

        assertNotNull(createdProduto);
        assertEquals(produtoDTO, createdProduto);
    }

    @Test
    void deveAtualizarUmProduto() {
        Long id = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", BigDecimal.valueOf(10.0), Categoria.BEBIDA);

        Produto produto = produtoDTO.toProduto();
        when(produtoRepository.updateProduto(ArgumentMatchers.eq(id), ArgumentMatchers.any())).thenReturn(produto);
        when(produtoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(produtoDTO);

        ProdutoDTO updatedProduto = produtoUseCase.updateProduto(id, produtoDTO);

        assertNotNull(updatedProduto);
        assertEquals(produtoDTO, updatedProduto);
    }

    @Test
    void deveBuscarProdutoPorId() {
        Long id = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", BigDecimal.valueOf(10.0), Categoria.BEBIDA);

        Produto produto = produtoDTO.toProduto();
        when(produtoRepository.findProdutoById(ArgumentMatchers.eq(id))).thenReturn(produto);
        when(produtoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(produtoDTO);

        ProdutoDTO foundProduto = produtoUseCase.getProdutoById(id);

        assertNotNull(foundProduto);
        assertEquals(produtoDTO, foundProduto);
    }

    @Test
    void deveDeletarProduto() {
        Long id = 1L;
        when(produtoRepository.deleteProduto(ArgumentMatchers.eq(id))).thenReturn(true);

        boolean isDeleted = produtoUseCase.deleteProduto(id);

        assertTrue(isDeleted);
    }

    @Test
    void deveBuscarTodosOsProdutos() {
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", BigDecimal.valueOf(10.0), Categoria.BEBIDA);
        Produto produto = produtoDTO.toProduto();

        when(produtoRepository.listAllProdutos()).thenReturn(Collections.singletonList(produto));
        when(produtoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(produtoDTO);

        List<ProdutoDTO> produtos = produtoUseCase.getAllProdutos();

        assertFalse(produtos.isEmpty());
        assertEquals(produtoDTO, produtos.get(0));
    }

    @Test
    void deveBuscarEListarProdutoPorCategoria() {
        Categoria categoria = Categoria.BEBIDA;
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", BigDecimal.valueOf(10.0), categoria);
        Produto produto = produtoDTO.toProduto();

        when(produtoRepository.listByCategoria(ArgumentMatchers.eq(categoria))).thenReturn(Collections.singletonList(produto));
        when(produtoMapper.domainToDto(ArgumentMatchers.any())).thenReturn(produtoDTO);

        List<ProdutoDTO> produtos = produtoUseCase.getProdutosByCategoria(categoria);

        assertFalse(produtos.isEmpty());
        assertEquals(produtoDTO, produtos.get(0));
    }
}
