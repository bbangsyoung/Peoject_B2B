package com.shop.Entity;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class MemberTest {


    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    @DisplayName("auditing 테스트")
    @WithMockUser(username = "seoyoung", roles = "USER") // 사용자가 로그인한 상태라고 가정하고 테스트진행
    public void auditingTest() {
        Member newMember = new Member();
        memberRepository.save(newMember);

        em.flush();
        em.clear();

        Member member = memberRepository.findById(newMember.getId())
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("register time" + member.getRegTime());
        System.out.println("register time" + member.getUpdateTime());
        System.out.println("register time" + member.getCreatedBy());
        System.out.println("register time" + member.getModifiedBy());

    }


}
