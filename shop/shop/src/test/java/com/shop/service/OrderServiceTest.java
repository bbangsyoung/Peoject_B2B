package com.shop.service;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import com.shop.vo.OrderVo;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;


    //테스트용 - 주문상품 저장
    public Item saveItem() {
        Item item = new Item();
        item.setItemNm("상품1");
        item.setPrice(1000);
        item.setItemDetail("상품1의 상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    //테스트용 - 주문회원 저장
    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order() {
        Item item = saveItem();
        Member member = saveMember();

        OrderVo orderVo = new OrderVo();
        orderVo.setCount(10); //주문수량
        orderVo.setItemId(item.getId()); //주문상품수량

        //주문번호로 저장된 주문정보 조회
        Long orderId = orderService.order(orderVo, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        //주문상품의 총가격
        int totalPrice = orderVo.getCount()*item.getPrice();

        //주문상품의 총가격과 DB에 저장된 상품의 가격을 비교하여 같으면 테스트 통과
        assertEquals(totalPrice, order.getTotalPrice());
    }











}
