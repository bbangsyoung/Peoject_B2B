package com.shop.service;


import com.shop.entity.*;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import com.shop.vo.OrderHistVo;
import com.shop.vo.OrderItemVo;
import com.shop.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

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
    private final ItemImgRepository itemImgRepository;

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


    @Transactional(readOnly = true)
    public Page<OrderHistVo> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable); //주문목록조회
        Long totalCount = orderRepository.countOrder(email); //주문총개수

        List<OrderHistVo> orderHistVos = new ArrayList<>();

        for(Order order : orders) { //주문리스트 순회. 구매이력 페이지에 전달할 Vo생성
            OrderHistVo orderHistVo = new OrderHistVo(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(),"Y");
                OrderItemVo orderItemVo = new OrderItemVo(orderItem, itemImg.getImgUrl());
                orderHistVo.addOrderItemVo(orderItemVo);
            }

            orderHistVos.add(orderHistVo);
        }
        return new PageImpl<OrderHistVo>(orderHistVos, pageable, totalCount); //페이지 구현객체 생성하여 반환
    }


    //주문취소
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {
        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();


        //현재 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지 체크 후 같으면 true 아니면 false 반환
        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
            return false;
        }
        return true;
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        //주문취소 상태로 변경하면 트랜잭션이 끝날 때 update쿼리 실행
        order.cancelOrder();
    }


    public Long orders(List<OrderVo> orderVoList, String email){

        Member member = memberRepository.findByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderVo orderVo : orderVoList) {
            Item item = itemRepository.findById(orderVo.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderItem orderItem = OrderItem.createOrderItem(item, orderVo.getCount());
            orderItemList.add(orderItem);
        }

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }














}
