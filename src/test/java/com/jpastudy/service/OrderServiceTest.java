package com.jpastudy.service;

import com.jpastudy.domain.Address;
import com.jpastudy.domain.Member;
import com.jpastudy.domain.OrderStatus;
import com.jpastudy.domain.Orders;
import com.jpastudy.domain.item.Book;

import com.jpastudy.domain.item.Item;
import com.jpastudy.exception.NotEnoughStockException;
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
        Member member = createMember();
        Book book = createItem("A",10,10);
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

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws  Exception {
        Member member = createMember();
        Item item = createItem("B",100,3);
        int orderCount = 3;
        orderService.order(member.getId(), item.getId(), orderCount);
        Member member2 = new Member();
        member2.setName("AA");


        fail("재고수량부족");
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

    private Member createMember() {
        Member member = new Member();
        member.setName("회원12");
        member.setAddress(new Address("서울","강가","123123"));
        em.persist(member);
        return member;
    }

    private Book createItem(String name, int price, int stockQty) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQty);
        em.persist(book);
        return book;
    }
}