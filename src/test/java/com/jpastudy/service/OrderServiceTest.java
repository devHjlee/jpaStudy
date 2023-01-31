package com.jpastudy.service;

import com.jpastudy.domain.Address;
import com.jpastudy.domain.Member;
import com.jpastudy.domain.OrderStatus;
import com.jpastudy.domain.Orders;
import com.jpastudy.domain.item.Book;

import com.jpastudy.domain.item.Item;
import com.jpastudy.repository.MemberRepository;
import com.jpastudy.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123123"));
        em.persist(member);
        Item book = new Book();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        book.setPrice(20000);
        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Orders getOrder = orderRepository.findOne(orderId);

        assertEquals("상품주문시 상대는 ORDER", OrderStatus.ORDER,getOrder.getStatus());

    }

    @Test
    public void 주문취소() throws Exception {

    }

    @Test
    public void 상품주문_재고수량초과() throws  Exception {

    }

    @Test
    public void 동일객체() throws Exception {
        Member member = memberRepository.findOne(39L);
        Member member2 = memberRepository.findOne(34L);
        System.out.println("영속성에 있으니 아래는 쿼리조회 안하겠지");
        Member member3 = memberRepository.findOne(39L);
        Member member4 = memberRepository.findOne(34L);
        System.out.println(member3 == member);

        //영속성해제
        //em.detach(member);
        member.setName("AAAAAAAA");
        //em.close();
    }
}