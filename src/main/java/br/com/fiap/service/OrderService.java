package br.com.fiap.service;

import br.com.fiap.model.OrderModel;
import br.com.fiap.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderModel createOrder(OrderModel orderModel) {
        return repository.save(orderModel);
    }

    public List<OrderModel> readAllOrders() {
        return repository.findAll();
    }

    public OrderModel findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
    }

    public OrderModel updateOrder(Long id, OrderModel orderModel) {
        return repository.findById(id).map(existingOrder -> {
            existingOrder.setClienteNome(orderModel.getClienteNome());
            existingOrder.setTotalValue(orderModel.getTotalValue());
            return repository.save(existingOrder);
        }).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    public void deleteOrderById(Long id) {
        try {
          repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Pedido não encontrado." + e.getMessage());
        }
    }
}
