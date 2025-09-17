package com.SpringBoot.Supply_Chain.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.Supply_Chain.Entity.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("select o from Orders o where o.trackingNumber=?1")
    Orders getOrderByTrackingNumber(long trackingNumber);

    @Query("select o from Orders o where o.totalAmount > ?1")
    List<Orders> getOrderGreaterThan(double price);

    @Query("select o from Orders o where o.status=?1")
    List<Orders> getOrderByStatus(String status);

    @Query("select o from Orders o where o.customer.id=?1")
    List<Orders> getOrderByCustomerId(int id);

    Page<Orders> findAll(Pageable pageable);
}
