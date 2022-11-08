package com.jpastudy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded//내장타입
    private Address address;
    @OneToMany(mappedBy = "member") // order entity member 필드 값을 넣는다
    private List<Orders> orders = new ArrayList<>();
}
