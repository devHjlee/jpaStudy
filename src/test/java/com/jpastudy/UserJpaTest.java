package com.jpastudy;

import com.jpastudy.domain.User;
import com.jpastudy.repository.UserRepository;
import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJpaTest {
    @Autowired
    UserRepository userRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void testUser() {
        User user = new User();
        user.setName("memberA");
        Long savedId = userRepository.save(user);
        User findMember = userRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(user.getId());

        Assertions.assertThat(findMember.getName()).isEqualTo("user.getName()");
        Assertions.assertThat(findMember).isEqualTo(user); //JPA 엔티티 동일성 보장
    }
}
