package vois.securitycoursedemo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity // will match the class with the table in the DB
// @Table(name = "customer") // if the table name is different from the class name
@Getter
@Setter // to generate the getters and setters for the class
@AllArgsConstructor
public class Customer {

    public Customer() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;

    @Nullable
    private String password;

    private String apiKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    public Customer(String email, String password, Set<Authority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
