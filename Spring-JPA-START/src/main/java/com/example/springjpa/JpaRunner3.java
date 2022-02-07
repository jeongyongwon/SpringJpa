package com.example.springjpa;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Component
@Transactional
public class JpaRunner3 implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("spring");

        System.out.println("lombok test   :  "  + post.getTitle());

        Comment comment = new Comment();
        comment.setComment("hello");

        postRepository.save(post);

        //페이지네이션 가볍게 구현가능
        Page<Post> postInfo = postRepository.findAll(PageRequest.of(0,10));
        List<Post> postInfoTest =  postRepository.findDistinctByTitle("spring");

        postInfoTest.forEach(x -> {
            System.out.println("test custom zzzzzzzzzzzzz : "   +  x.getTitle());
        });
    }
}
