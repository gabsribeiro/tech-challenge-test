package br.com.grupo27.techchallenge02.external.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo27.techchallenge02.adapters.controllers.PagamentoController;
import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;

@RestController
@RequestMapping("api/pagamento")
public class PagamentoApi {

    private final PagamentoController controller;

    public PagamentoApi(PagamentoController controller) {
        this.controller = controller;
    }

    @GetMapping("/gera-qrcode/{id}")
    public ResponseEntity<String> geraQrCodePedido(@PathVariable Long id) {
        return controller.geraQrCodePedido(id);
    }

    @GetMapping("/verifica-pagamento/{id}")
    public ResponseEntity<Boolean> verificaPagamento(@PathVariable Long id) {
        return controller.verificaPagamento(id);
    }

    @GetMapping("/status-pagamento")
    public ResponseEntity<List<PedidoDTO>> getPedidosByStatusPagamento(@RequestParam boolean pago) {
        return controller.getPedidosByStatusPagamento(pago);
    
    }

}
