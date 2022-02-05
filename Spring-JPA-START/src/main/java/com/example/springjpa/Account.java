package com.example.springjpa;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity //객체 세상에서 부르는 이름
//@Table이라는 annotation도 생략되있는거임
//Table은 릴레이션 세상에서 부르는 이름
// 기본적으로 아무것도 적지않고  @Entity만 잇으면 클래스이름으로 테이블이름이 따라가고
//@Entity(name="account") 이런식으로 줄 수 있고
//@Table까지 붙이고 @Entity에만 이름을 설정해준다면 @Table은 Entity의 name을 따라간ㄷ
public class Account {

    @Id @GeneratedValue
    private Long id;

    //사실상 @Column 이 생략되잇는거임임    private  String username;
    @Column(nullable = false, unique = true) //이런식으로 null이면 안되고 unique하게 만들수도 있다~이말
    private  String username;

    private  String password;

    @OneToMany(mappedBy = "owner") //반대쪽에서 이 이름으로 매핑 되잇어요~ + 해당 옵션을 적지 않고 각각 어노테이션을 쓰면
    //양방향 매핑이 아니라 각각의 단방향 매핑이다다
    private Set<Study> studies = new HashSet<>();

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    //javax.persistance 라이브러리 껄 사용해야함함
    @Temporal(TemporalType.TIMESTAMP) //이런식으로 Date 들어가는 형식도 정해줌
    private Date created = new Date(); // Default로 설정할 수도 있음
    @Transient  // 컬럼으로 인식안함함
   private String testFilter;

    //해당 클래스 내의 컬럼을 다 집어넣는다
    //Composit type
    //이런식으로 override로 street이라는 value를 해당 테이블에서는 home_street로 쓸수도 있다
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
//    })
//    private Address address;

    //getter and setter 생성단축기 Alt + insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //conveiner method랑 한다
    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
