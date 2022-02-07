package com.example.springjpa;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue @NotNull
    private  Long id;

    @Nullable
    private String title;

    @OneToMany(mappedBy = "post" , cascade = {CascadeType.ALL}) //Comment에 저장한걸 전파해주세요~ + 이외에도 remove등등 다양한 Casade 옵션이 있다 + ALL로 치면 편함
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    private int likecnt;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
}
