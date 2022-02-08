package com.example.springjpa;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner3 implements ApplicationRunner {
    //docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=yongwon -e POSTGRES_DB=jpa_pre --name postgres_boot -d postgres
    //docker exec -it postgres_boot psql -U yongwon jpa_pre
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        post.setTitle("spring");
        post2.setTitle("yongwon");
        post3.setTitle("Yongwon");

        System.out.println("lombok test   :  "  + post.getTitle());

        Comment comment = new Comment();
        comment.setComment("hello");

        postRepository.save(post);
        postRepository.save(post2);
        postRepository.save(post3);

        PageRequest pageRequest = PageRequest.of(1,2, Sort.by(Sort.Direction.ASC, "Id"));

        //페이지네이션 가볍게 구현가능
        //상속받는 인터페이스들도 좀 살피자
        //Page Spring Data에서 가져와야함
        Page<Post> postInfo = postRepository.findAll(pageRequest);

        List<Post> postInfoTest =  postRepository.findDistinctByTitle("spring");
        List<Post> postUpperData =  postRepository.findByTitleContains("yongwon");
        List<Post> postUpperDataIgnore =  postRepository.findByTitleContainsIgnoreCase("yongwon");
        List<Post> postGreaterTest =  postRepository.findByTitleContainsIgnoreCaseAndIdGreaterThan("spring",3l);
        List<Post> postOrderTest =  postRepository.findByTitleContainsIgnoreCaseAndIdGreaterThanOrderByIdDesc("spring",1l);

        //Stream도 한번보자~~~~~~~~~~~~~~~~~~~~~~~~

        postInfo.forEach(x -> {
            System.out.println("postOrderTest = " + x.getTitle());
        });
//        postInfoTest.forEach(x -> {
//            System.out.println("test custom zzzzzzzzzzzzz : "   +  x.getTitle());
//        });
    }
}
