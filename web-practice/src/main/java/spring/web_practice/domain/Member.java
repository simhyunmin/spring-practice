package spring.web_practice.domain;

import jakarta.persistence.*;

//JPA가 관리하는 엔티티라는 뜻
@Entity
public class Member {

    //pk를 매핑해줘야 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
