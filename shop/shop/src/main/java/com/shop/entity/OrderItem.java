package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue //PK
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //N:1. 지연로딩.
    @JoinColumn(name ="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) //N:1. 지연로딩.
    @JoinColumn(name = "order_id")
    private Order order;

    private  int orderPrice; //주문가격
    private int count; //수량

//    private LocalDateTime regTime;
//    private LocalDateTime upDateTime;



    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문상품
        orderItem.setCount(count); //주문수량
        orderItem.setOrderPrice(item.getPrice()); //상품가격을 주문가격으로 세팅

        //주문수량만큼 재고감소
        item.removeStock(count);
        return orderItem;
    }

    //총 금액 계산
    public int getTotalPrice(){
        return orderPrice*count;
    }

    //주문 취소시 주문수량만큼 상품의 재고를 더해줌
    public void cancel() {
        this.getItem().addStock(count);
    }
}
