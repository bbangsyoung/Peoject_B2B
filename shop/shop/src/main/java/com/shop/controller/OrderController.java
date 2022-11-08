package com.shop.controller;

import com.shop.service.OrderService;
import com.shop.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderVo orderVo,
                                              BindingResult bindingResult, Principal principal) {

        //주문정보 받는 orderVO객체에 데이터 바인딩 시 에러가 있는지 체크
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
            //에러 정보를 ResponseEntity객체에 담아 반환
        }

        //현재 로그인 회원의 이메일 정보를 조회
        String email = principal.getName();
        Long orderId;




        return null;
    }











}

