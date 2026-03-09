package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Member() {}
    public Member(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}