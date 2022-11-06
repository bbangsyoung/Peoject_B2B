package com.shop.controller;

import com.shop.entity.Member;
import com.shop.service.MemberService;
import com.shop.vo.MemberFormVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password) {
        MemberFormVo memberFormVo = new MemberFormVo();
        memberFormVo.setEmail(email);
        memberFormVo.setName("김민규");
        memberFormVo.setAddress("경기도 안산시 상록구");
        memberFormVo.setPassword(password);

        Member member = Member.createMember(memberFormVo, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email = "test@email.com";
        String password ="1234";
        this.createMember(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login") //회원가입 메소드 실행 후 로그인이 되는지 테스트
                .user(email).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated()); //로그인 성공시 테스트코드 통과
    }



}
