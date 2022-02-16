package com.example.hateoas.commonweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc //application.propertiese를 설정
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public  void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        mockMvc.perform(get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

    @Test
    public  void getPosts() throws Exception {
        createPosts();
        mockMvc.perform(get("/posts/")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "created,desc")
                        .param("sort", "title")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("jpa")));
    }

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");

        //이렇게 return 받은 instance를 사용하는게 좋다 !!!****
        // savedPost는 persist 상태의 객체이기 때문에 post는 아직 아니지~
        Post savedPost = postRepository.save(post); //사실 해당 값을 리턴해주는것과 같다  _ persist
        //다시해보자
        //assertThat(entityManager.contains(post)).isTrue(); //persist manage가 post라는 entity를 cacheing 하고 있음
        //assertThat(entityManager.contains(savedPost)).isTrue(); // 얘도 entitymanager가 가지고 있음
        //assertThat(savedPost == post);


        Post postUpdate = new Post();

        postUpdate.setId(post.getId());
        postUpdate.setTitle("jpa"); //알아서 캐싱처리해줌  + 진ㅉ fancy하다
        Post updatedPost = postRepository.save(postUpdate); //update 쿼리 발생함  + 얜 merge가 발생함 왜 ? id가 이미 있기때문에


        List<Post> all = postRepository.findAll();
        System.out.println("all  :  "   +  all);
        //assertThat(entityManager.contains(updatedPost)).isTrue();
        //assertThat(entityManager.contains(postUpdate)).isFalse();
        //assertThat(updatedPost == postUpdate);
    }

    @Test
    public  void NamedQuery() throws Exception {
        createPosts();
        List<Post> all = postRepository.yongCustom("Spring", Sort.by("title"));
        List<Post> all2 = postRepository.yongCustom("Spring", JpaSort.unsafe("LENGTH(title)")); //JpaSort 을 활용해 프로퍼티나 alias 아닌 함수 결과값을 정렬기준으로 사용할 수 있음
        assertThat(all.size()).isEqualTo(1);
    }

    private void createPosts() {
//        int postCount = 100;
//        while(postCount > 0){
//            Post post = new Post();
//            post.setTitle("Spring Data Jpa");
//            postRepository.save(post);
//            postCount--;
//        }

        Post post = new Post();
        post.setTitle("Spring");
        postRepository.save(post);
    }
}