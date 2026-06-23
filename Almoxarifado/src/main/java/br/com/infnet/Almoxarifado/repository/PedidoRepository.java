package br.com.infnet.Almoxarifado.repository;

import br.com.infnet.Almoxarifado.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
