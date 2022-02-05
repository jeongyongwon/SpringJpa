package com.example.springjpa;


import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("yongwon");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("Spring Data Jpa");
        //study.setOwner(account);

        //관계의 주인인 쪽에 매핑 해야한다!!!! 아주 아주 중요함
        //아래 내용식으로 넣어주자
        account.addStudy(study);

        //세션에도 저장가능
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
        //entityManager.persist(account);

    }
}
