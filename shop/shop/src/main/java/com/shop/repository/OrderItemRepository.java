package com.shop.repository;

import com.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

//주문 객체 조회테스트용(즉시로딩)
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
