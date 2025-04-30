package br.com.fiap.controller;

import br.com.fiap.model.OrderModel;
import br.com.fiap.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderModel order) {
        try {
            OrderModel orderModel = orderService.createOrder(order);
            return new ResponseEntity<>(orderModel, HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders")
    public List<OrderModel> readOrders() {
        return orderService.readAllOrders();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderModel order) {
        try {
            OrderModel orderModel = orderService.updateOrder(id, order);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id){
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
