package com.fict.services;

import com.fict.entities.Creditor;
import com.fict.entities.Order;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface OrderService {

    Page<Order> findOrdersByUser(int pageNumber, int limit, Principal principal);

    List<Order> findOrdersByCustomerEmail(String email);

    Order findOrderById(Long id);

    List<Order> findOrdersByCreditor(Creditor creditor);

    Order saveOrder(Order order, Principal principal);

    Page<Order> findAll(int pageNumber, int limit);

    Order editOrder(Order order);

}
