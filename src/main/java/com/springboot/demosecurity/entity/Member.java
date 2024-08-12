package com.springboot.demosecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "members")
@Data
public class Member {
    @Column(name = "user_id")
    @Id
    private String userId;
    private String pw;
    private int active;
}
