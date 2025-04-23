package br.com.fiap.repository;

import br.com.fiap.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
