package com.shop.repository;

import com.shop.entity.Item;
import com.shop.vo.ItemSearchVo;
import com.shop.vo.MainItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    //상품조회조건을 담고있는 itemSearchVo 객체와
    //페이징 정보를 담고있는 pageable객체를 파라미터로 받는 메소드를 정의
    Page<Item> getAdminItemPage(ItemSearchVo itemSearchVo, Pageable pageable);
    Page<MainItemVo> getMainItemPage(ItemSearchVo itemSearchVo, Pageable pageable);

}
