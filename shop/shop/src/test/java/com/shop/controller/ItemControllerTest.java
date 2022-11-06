package com.shop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("상품등록 페이지 권한 테스트 / ADMIN")
    @WithMockUser(username = "admin", roles = "ADMIN")  //로그인상태 : 관리자(ADMIN)
    public void itemFormTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) //get요청
                .andDo(print())
                .andExpect(status().isOk()); //응답코드가 정상인지 확인
    }

    @Test
    @DisplayName("상품등록 페이지 권한 테스트 / USER")
    @WithMockUser(username = "user", roles = "USER")  //로그인상태 : 일반회원(USER)
    public void itemFormUSERTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) //get요청
                .andDo(print())
                .andExpect(status().isForbidden()); //Forbidden 오류가 발생하면 통과
    }



}
