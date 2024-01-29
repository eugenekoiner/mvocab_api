package com.mvocab.mvocab_api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String name;
    private String phone;
}
