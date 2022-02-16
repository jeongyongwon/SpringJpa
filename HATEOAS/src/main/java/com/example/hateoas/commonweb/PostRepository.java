package com.example.hateoas.commonweb;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitleContains(String keyword);

//    @Query( value = "SELECT p FROM Post As p where p.title = ?1 " , nativeQuery = false) //Entity 썻던거 여기 써도됨 + nativeQuery를 여기다 써도됨
//    List<Post> yongCustom(String keyword, Sort sort);

    @Query( value = "SELECT p FROM #{#entityName} As p where p.title = :title " , nativeQuery = false) // 이런식으로 파라미터 바인딩도 가능 + entity 이름도 동적으로
    List<Post> yongCustom(@Param("title") String keyword, Sort sort);
}
