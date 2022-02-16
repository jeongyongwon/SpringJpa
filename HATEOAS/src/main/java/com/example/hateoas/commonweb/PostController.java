package com.example.hateoas.commonweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired(required = true)
    private PostRepository posts;

    @GetMapping("/posts/{id}")
    public String getAPost(@PathVariable Long id) {
        Optional<Post> byId = posts.findById(id);
        Post post = byId.get();
        return post.getTitle();
    }

    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return posts.findAll(pageable);
    }
}
