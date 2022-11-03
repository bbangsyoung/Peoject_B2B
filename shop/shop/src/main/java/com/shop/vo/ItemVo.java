package com.shop.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemVo {

    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}
