package com.shop.vo;

import com.shop.constant.OrderStatus;
import com.shop.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//주문정보를 담는 클래스
@Getter @Setter
public class OrderHistVo {
    private Long orderId; //주문아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문상태
    private List<OrderItemVo> orderItemVoList = new ArrayList<>(); //주문상품 리스트

    public OrderHistVo(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); //날짜형태 포맷수정
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemVo(OrderItemVo orderItemVo){ //Vo객체를 주문상품 리스트에 추가
        orderItemVoList.add(orderItemVo);
    }


}
