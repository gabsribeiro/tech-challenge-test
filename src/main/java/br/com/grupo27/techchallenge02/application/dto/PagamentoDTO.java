package br.com.grupo27.techchallenge02.application.dto;

public record PagamentoDTO(
    Long id,
    String idCobranca,
    String idtx,
    Long idPedido
) {

}
