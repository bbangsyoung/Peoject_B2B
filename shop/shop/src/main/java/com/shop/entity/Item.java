package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.exception.OutOfStockException;
import com.shop.vo.ItemFormVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    //재고처리
    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber; //기존재고에서 새로 들어온 재고 빼기
        if(restStock<0) {
            //재고가 부족할 경우 예외발생
            throw  new OutOfStockException("상품 재고가 부족합니다. 현재 재고 수량 : " + this.stockNumber);
        }
        this.stockNumber = restStock; //주문 후 남은 재고 수량을 상품의 현재 재고값으로 할당
    }


    public void updateItem(ItemFormVo itemFormVo) {
        this.itemNm = itemFormVo.getItemNm();
        this.price = itemFormVo.getPrice();
        this.stockNumber = itemFormVo.getStockNumber();
        this.itemDetail = itemFormVo.getItemDetail();
        this.itemSellStatus = itemFormVo.getItemSellStatus();
    }


    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    private LocalDateTime regTime; //등록시간
    private LocalDateTime updateTime; //수정시간









}