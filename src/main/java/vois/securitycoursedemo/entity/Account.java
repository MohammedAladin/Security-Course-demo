package vois.securitycoursedemo.entity;
import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Adding a primary key

    @Column(name = "balance")
    private double balance;

    public Account() {
    }
    @JoinColumn(table = "customer", referencedColumnName = "id")
    public Long customerId;

    public Account(Long customerId, double balance) {
        this.balance = balance;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

}
