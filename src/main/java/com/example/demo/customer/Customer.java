package com.example.demo.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;

    private Long created;
    private Long updated;

    private String fullName;
    private String email;
    private String phone;

    private boolean isActive;

    public Customer(String fullName,
                    String email,
                    String phone) {
        this.created = System.currentTimeMillis();
        this.fullName = fullName;
        this.isActive = true;
        this.email = email;
        this.phone = phone;
    }

    public Customer(String fullName,
                    String email) {
        this.created = System.currentTimeMillis();
        this.isActive = true;
        this.fullName = fullName;
        this.email = email;
    }

    public Customer() {

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }


    public void setCreated(Long created) {
        this.created = created;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }
    @JsonIgnore
    public Long getCreated() {
        return created;
    }
    @JsonIgnore
    public Long getUpdated() {
        return updated;
    }

    @JsonIgnore
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "fullName = " + fullName +
                ",email = " + email  +
                ",id = " + id + '}';
    }
}
