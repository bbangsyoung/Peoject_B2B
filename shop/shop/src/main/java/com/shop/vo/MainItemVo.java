package com.shop.vo;

<<<<<<< Updated upstream
=======
import com.querydsl.core.annotations.QueryProjection;
>>>>>>> Stashed changes
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemVo {
    private Long id;
    private String itemNm;
    private String itemDetail;
    private String imgUrl;
    private Integer price;

<<<<<<< Updated upstream


    

=======
    @QueryProjection
    public MainItemVo(Long id, String itemNm, String itemDetail, String imgUrl, Integer price) {
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
>>>>>>> Stashed changes

}
