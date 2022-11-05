package com.shop.service;

import com.shop.entity.Member;
import com.shop.vo.MemberFormVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //회원정보 입력해서 만드는 메소드
    public Member createMember() {
        MemberFormVo memberFormVo = new MemberFormVo();
        memberFormVo.setEmail("test@naver.com");
        memberFormVo.setName("김미려");
        memberFormVo.setAddress("서울시 마포구 합정동");
        memberFormVo.setPassword("1234");
        return Member.createMember(memberFormVo, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        //요청값과 실제 저장된 데이터 비교
        //첫번째 파라미터는 요청값, 두번째 파라미터는 실제 저장된 값
        assertEquals(member.getEmail(), saveMember.getEmail());
        assertEquals(member.getName(), saveMember.getName());
        assertEquals(member.getAddress(), saveMember.getAddress());
        assertEquals(member.getPassword(), saveMember.getPassword());
        assertEquals(member.getRole(), saveMember.getRole());
    }


    @Test
    @DisplayName("중복회원가입 테스트")
    public void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        //예외처리테스트
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다.",e.getMessage());

    }


}
