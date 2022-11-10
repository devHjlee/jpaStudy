package com.jpastudy;

import com.jpastudy.domain.Member;
import com.jpastudy.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberJpaTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void test(){
        List<Member> resultList = memberRepository.findName("1");
        for (Member rs:resultList) {
            System.out.println(rs.getId()+":"+rs.getName());
        }
    }
}
