package com.example.querydsl.account;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

//생각보다 자동 import가 븅신같음
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    //https://atoz-develop.tistory.com/entry/IntelliJ-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%86%8C%EC%8A%A4-%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%ACsrc-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
    //쿼리 Q* 관련 Source 세팅을 위해 인텔리제이 기능을 알아두자
    @Test
    public void crud() {
        QAccount account = QAccount.account;
        Predicate predicate = account
                .firstName.containsIgnoreCase("yongwon")
                .and(account.lastName.startsWith("jeong"));

        Optional<Account> one = accountRepository.findOne(predicate);
        assertThat(one).isEmpty();
    }

}