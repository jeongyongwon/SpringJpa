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

        //이미 윗 단계에서 트랜잭션이 끝남
        Account yongwon = session.load(Account.class, account.getId());

        //신기하게도 hibernate가 dirty checking을 통해 db에 반영해주고 아래 코드가 반영됨됨
        //로그를 보면 업데이트를 해줬다
        yongwon.setUsername("fucking");
        //그러나 윗 단계에서보면 이미 넣었던 정보기 때문에 캐시가 작용하여 update문을 사용하지 않음
        yongwon.setUsername("yongwon");
        System.out.println("==================");
        System.out.println(yongwon.getUsername());

    }
}
