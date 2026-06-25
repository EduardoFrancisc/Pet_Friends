package br.com.infnet.Pedidos.repository;

import br.com.infnet.Pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
