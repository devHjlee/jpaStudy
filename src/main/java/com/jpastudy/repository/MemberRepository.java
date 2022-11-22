package com.jpastudy.repository;

import com.jpastudy.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //@PersistenceUnit EntityManagerFactory를 주입하여 직접 사용가능
    //private EntityManagerFactory emf;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class).setParameter("name",name).getResultList();
    }
}
