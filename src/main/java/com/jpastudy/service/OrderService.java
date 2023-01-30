package com.jpastudy.service;

import com.jpastudy.domain.Delivery;
import com.jpastudy.domain.Member;
import com.jpastudy.domain.OrderItem;
import com.jpastudy.domain.Orders;
import com.jpastudy.domain.item.Item;
import com.jpastudy.repository.ItemRepository;
import com.jpastudy.repository.MemberRepository;
import com.jpastudy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //@NoArgsConstructor(access = AccessLevel.PROTECTED) protected로 막아줘야 소스가 통일이 된다
        //OrderItem orderItem1 = new OrderItem();


        //주문생성
        Orders order = Orders.createOder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    //아래처럼 서비스계층에 비즈니스 로직이 없고 도메인에 비지니스 로직이 있는걸 도메인모델 패턴
    //기존 프로젝트처럼 서비스계층에 로직이 있고 도메인은 단순 getter,setter만 있을때는 트랜잭션 스크립트 패턴
    @Transactional
    public void cancelOrder(Long orderId){
        Orders order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
