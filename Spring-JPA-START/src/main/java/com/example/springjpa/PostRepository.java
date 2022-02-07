package com.example.springjpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

        //@Query(value = "SELECT FROM Post p WHERE p.title= keyword",nativeQuery = true)
        List<Post> findByTitleContains(String keyword);

        List<Post> findDistinctByTitle(String keyword);

}
