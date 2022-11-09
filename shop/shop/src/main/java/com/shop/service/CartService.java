package com.shop.service;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.vo.CartDetailVo;
import com.shop.vo.CartItemVo;
import com.shop.vo.CartOrderVo;
import com.shop.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemVo cartItemVo, String email) {
        Item item = itemRepository.findById(cartItemVo.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }
        CartItem savedCartItem =
                cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null) {
            savedCartItem.addCount(cartItemVo.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemVo.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }




    @Transactional(readOnly = true)
    public List<CartDetailVo> getCartList(String email){

        List<CartDetailVo> cartDetailVoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){
            return cartDetailVoList;
        }

        cartDetailVoList = cartItemRepository.findCartDetailVoList(cart.getId());
        return cartDetailVoList;
    }




    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Member curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }


    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem =cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }



    public Long orderCartItem(List<CartOrderVo> cartOrderVoList, String email){
        List<OrderVo> orderVoList = new ArrayList<>();

        for (CartOrderVo cartOrderVo : cartOrderVoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderVo.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderVo orderVo = new OrderVo();
            orderVo.setItemId(cartItem.getItem().getId());
            orderVo.setCount(cartItem.getCount());
            orderVoList.add(orderVo);
        }

        Long orderId = orderService.orders(orderVoList, email);
        for (CartOrderVo cartOrderVo : cartOrderVoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderVo.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }



}