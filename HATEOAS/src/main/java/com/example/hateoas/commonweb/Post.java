package com.example.hateoas.commonweb;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NamedQuery(name = "Post.yongCustom", query = "SELECT p FROM Post As p where p.title = ?1 ") //JPQL 첫번째 인자랑 같으면 찾아달라  + 사실 여기다 쓰는것도 좀 그래서 Repo쪽에 써줘도됨
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private  String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
