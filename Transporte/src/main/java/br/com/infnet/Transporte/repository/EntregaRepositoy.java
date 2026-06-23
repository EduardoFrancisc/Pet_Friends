package br.com.infnet.Transporte.repository;

import br.com.infnet.Transporte.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepositoy extends JpaRepository<Entrega, Integer> {
}
