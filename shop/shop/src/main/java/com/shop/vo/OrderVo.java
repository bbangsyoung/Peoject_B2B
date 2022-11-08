package com.shop.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//주문 상품 아이디, 주문수량
@Getter @Setter
public class OrderVo {

    @NotNull(message = "상품 아이디는 필수입력 값입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 주문수량은 1개 입니다.")
    @Max(value = 9999, message = "최대 주문수량은 9999개 입니다.")
    private int count;




}
