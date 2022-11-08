package com.jpastudy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)//default ORDINAL 숫자 EX: READY 1 COMP 2 >>> READY XXX COMP 중간에 새로운 타입이 추가될시 장애날 수 있으니 String 필수
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}
