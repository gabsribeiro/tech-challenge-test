package br.com.grupo27.techchallenge02.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.grupo27.techchallenge02.adapters.gateways.ClienteGateway;
import br.com.grupo27.techchallenge02.adapters.gateways.PagamentoGateway;
import br.com.grupo27.techchallenge02.adapters.gateways.PedidoGateway;
import br.com.grupo27.techchallenge02.adapters.gateways.ProdutoGateway;
import br.com.grupo27.techchallenge02.adapters.mappers.PagamentoMapper;
import br.com.grupo27.techchallenge02.adapters.mappers.PedidoMapper;
import br.com.grupo27.techchallenge02.adapters.mappers.ProdutoMapper;
import br.com.grupo27.techchallenge02.application.usecases.ClienteUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.PagamentoUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.PedidoUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.ProdutoUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.pix.PixConsultaPagamentoUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.pix.PixGeraCobrancaUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.pix.PixGeraQRCodeUseCaseImpl;
import br.com.grupo27.techchallenge02.application.usecases.pix.PixUseCaseImpl;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PagamentoUsecase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.PedidoUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixConsultaCobrancaUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixGeraCobrancaUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixGeraQRCodeUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.PedidoGatewayImpl;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.PedidoJPA;

@Configuration
public class ServiceConfig {

    @Bean
    public PedidoUseCaseImpl pedidoUseCase(PedidoGatewayImpl pedidoGateway, PedidoMapper pedidoMapper, ClienteGateway clienteGateway,PagamentoUsecase pagamento) {
        return new PedidoUseCaseImpl(pedidoGateway, pedidoMapper, clienteGateway, pagamento);
    }

    @Bean
    public PixUseCaseImpl pixUseCase(PixGeraCobrancaUseCase pixCobranca, PixGeraQRCodeUseCase pixQrCode, PixConsultaCobrancaUseCase pixConsulta) {
        return new PixUseCaseImpl(pixCobranca, pixQrCode, pixConsulta);
    }

    @Bean
    public ClienteUseCaseImpl clienteUseCase(ClienteGateway clienteRepository) {
        return new ClienteUseCaseImpl(clienteRepository);
    }

    @Bean
    public ProdutoUseCaseImpl produtoUseCase(ProdutoGateway produtoRepository, ProdutoMapper produtoMapper) {
        return new ProdutoUseCaseImpl(produtoRepository, produtoMapper);
    }

    @Bean 
    public PagamentoUseCaseImpl pagamentoUseCase(PedidoGateway pedidoGateway, PedidoMapper pedidoMapper, PixUseCase pix, PagamentoGateway pagamentoGateway, PagamentoMapper pagamentoMapper){
        return new PagamentoUseCaseImpl(pedidoGateway, pedidoMapper, pix, pagamentoGateway, pagamentoMapper);
    }

    @Bean
    public PixConsultaPagamentoUseCaseImpl pixConsultaPagamentoUseCase(Credenciais credentials){
        return new PixConsultaPagamentoUseCaseImpl(credentials);
    }

    @Bean
    public PixGeraQRCodeUseCaseImpl pixGeraQRCodeUseCase(Credenciais credentials){
        return new PixGeraQRCodeUseCaseImpl(credentials);
    }

    @Bean
    public PixGeraCobrancaUseCaseImpl pixGeraCobrancaUseCase(Credenciais credentials){
        return new PixGeraCobrancaUseCaseImpl(credentials);
    }

}
