package com.shop.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderVo {

    private Long cartItemId;
    private List<CartOrderVo> cartOrderVoList;
}
