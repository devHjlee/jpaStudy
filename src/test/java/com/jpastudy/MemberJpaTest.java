package com.jpastudy;

import com.jpastudy.domain.Member;
import com.jpastudy.repository.MemberRepository;
import com.jpastudy.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberJpaTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    @Rollback(false)
    public void 중복회원예외() throws Exception{
        //Given
        Member member = new Member();
        member.setName("kim");

        Member member2 = new Member();
        member.setName("kim");

        //when
        memberService.join(member);
        memberService.join(member2);

        //then
        fail("예외 발생");
    }
}
