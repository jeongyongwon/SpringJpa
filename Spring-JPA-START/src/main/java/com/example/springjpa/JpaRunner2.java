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
public class JpaRunner2 implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("Spring-data-jpa는 언제보나");

        Comment comment = new Comment();
        comment.setComment("빨리좀 보고싶다");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드릴께요");
        post.addComment(comment1);
        //세션에도 저장가능(hibernate 세션임)
        Session session = entityManager.unwrap(Session.class);
        session.save(post);


        //삭제했을때 구문
//        Post post1 = session.get(Post.class, 1l); // Long이니까 뒤에 붙여줌
//        session.delete(post1);

//        Post post2 = session.get(Post.class, 3l);
//        post2.getComments().forEach(x -> {
//            System.out.println("===============================");
//            System.out.println(x.getComment());
//        });

    }
}
