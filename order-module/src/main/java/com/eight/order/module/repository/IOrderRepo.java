package com.eight.order.module.repository;

import com.eight.order.module.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Order, String> {


}
