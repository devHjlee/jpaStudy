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

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJpaTest {

    @PersistenceUnit //EntityManagerFactory를 주입하여 직접 사용가능
    private EntityManagerFactory emf;

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
