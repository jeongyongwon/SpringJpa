package com.example.hateoas.commonweb;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//연관관계 정의
// repo에 커스텀 정의 해야함
//관계 설정햇기때문에 Eager로 가져옴
@NamedEntityGraph(name = "Comment.post"
        , attributeNodes = @NamedAttributeNode("post"))
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public boolean isBest() {
        return best;
    }

    //@ManyToOne(fetch = FetchType.EAGER) // Post까지 다 가져옴
    @ManyToOne(fetch = FetchType.LAZY) // Comment만 가져옴
    private  Post post;

    private  int up;

    private int down;

    private boolean best;

}
