package br.com.grupo27.techchallenge02.adapters.mappers;

import org.springframework.stereotype.Component;

import br.com.grupo27.techchallenge02.application.dto.PagamentoDTO;

import br.com.grupo27.techchallenge02.domain.model.Pagamento;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.PagamentoEntity;

@Component
public class PagamentoMapper {
        public PagamentoDTO domainToDto(Pagamento pagamento){
                return new PagamentoDTO(pagamento.getId()
                        ,pagamento.getIdCobranca()
                        ,pagamento.getIdtx()
                        ,pagamento.getIdPedido());
        }

        public PagamentoEntity domainToEntity(Pagamento pagamento) {
                return new PagamentoEntity(pagamento.getId()
                        ,pagamento.getIdCobranca()
                        ,pagamento.getIdtx()
                        ,pagamento.getIdPedido());
        }

        public Pagamento entityToDomain(PagamentoEntity pagamentoEntity) {
                return new Pagamento(pagamentoEntity.getId()
                        ,pagamentoEntity.getIdCobranca()
                        ,pagamentoEntity.getIdtx()
                        ,pagamentoEntity.getIdPedido());
        }
    
    public Pagamento dtoToDomain(PagamentoDTO pagamentoDTO) {
        return new Pagamento(pagamentoDTO.id()
                ,pagamentoDTO.idCobranca()
                ,pagamentoDTO.idtx()
                ,pagamentoDTO.idPedido());
    }

}
