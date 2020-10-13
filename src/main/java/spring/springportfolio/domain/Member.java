package spring.springportfolio.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//db에 숫자 자동 생성 하는 identity설정해서 쓴것
    private Long id;
    //@Column("username")만약 db엔 username 이라고 column을 설정했다면 이렇게 써야함
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
