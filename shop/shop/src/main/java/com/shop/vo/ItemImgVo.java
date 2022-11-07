package com.shop.vo;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter @Setter
public class ItemImgVo {

    private Long id;
    private String imgName;
    private String oriImgName;

    private String imgUrl;
    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper(); //멤버변수로 객체추가

    public static ItemImgVo of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgVo.class);
        //ItemImg 객체의 자료형과 멤버변수 이름이 갚을 때 ItemImgVo로 값을 복사해서 반환.
    }

}
