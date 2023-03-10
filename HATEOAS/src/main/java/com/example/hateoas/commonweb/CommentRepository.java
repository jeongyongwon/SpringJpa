package com.example.hateoas.commonweb;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = "post")
    Comment getById(Long id);

    <T> List<T> findByPost_Id(Long id, Class<T> type);


}
