package vois.securitycoursedemo.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "authority")
@Getter
public class Authority {

    public Authority() {}
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    @NaturalId
    @Getter
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Authority(String name) {
        this.name = name;
    }

}