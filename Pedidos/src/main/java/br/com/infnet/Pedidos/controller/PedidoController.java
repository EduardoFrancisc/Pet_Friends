package br.com.infnet.Pedidos.controller;

import br.com.infnet.Pedidos.dto.PedidoRequestDTO;
import br.com.infnet.Pedidos.model.Pedido;
import br.com.infnet.Pedidos.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequestDTO dto) {
        Pedido pedido = pedidoService.processarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
