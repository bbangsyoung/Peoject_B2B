package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //정렬할 때 사용하는 order, 맵핑 테이블 orders
@Getter
@Setter
public class Order {

    @Id @GeneratedValue //PK
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //N:1. 지연로딩.
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; //주문일

    @Enumerated(EnumType.STRING) //enum의 값을 index가 아닌 텍스트 값 그대로 저장
    private OrderStatus orderStatus; //주문상태

    //Order 객체가 주인이 아니므로 mappedBy 속성으로 연관관계의 주인을 설정.
    //즉, 연관관계의 주인 필드인 order를 mappedBy 값으로 세팅하면 됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //부모객체의 영속성 상태변화를 자식객체에 모두 전이. 고아객체 orphanRemoval = true 삭제
    private List<OrderItem> orderItems = new ArrayList<>(); //하나의 주문이 여러개의 주문상품을 가짐(List)

    private LocalDateTime regTime;
    private LocalDateTime updateTime;






}
