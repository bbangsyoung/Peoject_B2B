package com.shop.Entity;

import com.shop.entity.Cart;
import com.shop.entity.Member;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;
import com.shop.vo.MemberFormVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext //영속성 컨텍스트(JPA내부동작)
    EntityManager em;
    
    public Member createMember() {
        MemberFormVo memberFormVo = new MemberFormVo();
        memberFormVo.setEmail("test@email.com");
        memberFormVo.setName("김윤수");
        memberFormVo.setAddress("부산 어드메");
        memberFormVo.setPassword("1234");
        return Member.createMember(memberFormVo, passwordEncoder);
    }

    @Test
    @DisplayName("카트 - 회원 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();  //회원 엔티티와 카트 엔티티를 영속성 컨텍스트에 저장 후 강제로 flush 호출 -> DB반영
        em.clear();  //실제 DB에서 카트 엔티티를 가지고올떄 회원 엔티티도 가져오는지 보기위해 영속성 컨텍스트 비워둠

        Cart savedCart = cartRepository.findById(cart.getId())  //저장된 카트 엔티티 조회
                .orElseThrow(EntityNotFoundException::new); //처음 저장한 멤버 id와 카트에 저장된 멤버 id비교



    }
    

}
