package com.shop.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass //상속 받는 자식 클래스에 매핑정도만 제공하겠단 선언
@Getter @Setter
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false) //객체 생성 및 저장 시 자동으로 시간 저장
    private LocalDateTime regTime;

    @LastModifiedDate //객체 값 변경 시 자동으로 시간 저장
    private LocalDateTime updateTime;


}
