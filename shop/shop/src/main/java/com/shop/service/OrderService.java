package com.shop.service;


import com.querydsl.codegen.ParameterizedTypeImpl;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import com.shop.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

//주문처리로직
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderVo orderVo, String email) {

        //주문상품조회
        Item item = itemRepository.findById(orderVo.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        //이메일로 회원정보 조회
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderVo.getCount()); //주문할 상품 객체+주문수량으로 주문상품 객체 생성
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList); //회원정보+주문할상품으로 주문 객체 생성
        orderRepository.save(order); //주문객체 저장

        return order.getId();
    }


}
