package com.shop.vo;


import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormVo {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수입력값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgVo> itemImgVoList = new ArrayList<>(); //상품 저장 후 수정 시 이미지 정보를 저장하는 리스트

    private List<Long> itemImgIds = new ArrayList<>(); // 상품이미지 아이디를 저장(이미지 아이디를 담아둘 용도)

    private static ModelMapper modelMapper = new ModelMapper();

    //모델맵퍼를 이용해 객체와 VO객체간의 데이터를 복사하여 반환
    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    //모델맵퍼를 이용해 객체와 VO객체간의 데이터를 복사하여 반환
    public static ItemFormVo of(Item item) {
        return modelMapper.map(item, ItemFormVo.class);
    }

}
