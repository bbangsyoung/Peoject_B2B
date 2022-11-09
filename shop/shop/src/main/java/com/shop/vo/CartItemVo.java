package com.shop.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartItemVo {

    @NotNull(message = "상품 아이디는 필수입력 값입니다.")
    private Long itemId;
    
    @Min(value = 1, message = "최소 1개 이상 담아주세요!")
    private int count;




}
