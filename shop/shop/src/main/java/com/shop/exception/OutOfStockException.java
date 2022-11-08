package com.shop.exception;

//재고부족일 때 Exception 발생
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);
    }
}
