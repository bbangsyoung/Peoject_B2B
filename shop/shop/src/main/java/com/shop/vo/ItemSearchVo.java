package com.shop.vo;

import com.querydsl.codegen.ParameterizedTypeImpl;
import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchVo {

    //현재 시간과 상품등록일을 비교해서 삼품데이터 조회.
    private String searchDateType;

    //상품판매상태를 기준으로 상품데이터 조회
    private ItemSellStatus searchSellStatus;

    //상품조회 시 어떤 유형으로 조회할지 선택
    //itemNm:상품명 / createdBy:상품등록자아이디
    private String searchBy;

    //조회할 검색어를 저장할 변수
    //searchBy가 itemNm일 경우 상품명을 기준, createdBy일 경우 상품드옥자 아이디 기준
    private String searchQuery = "";




}
