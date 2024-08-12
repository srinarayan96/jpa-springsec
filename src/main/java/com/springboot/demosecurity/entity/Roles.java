package com.springboot.demosecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@IdClass(Roles_PK.class)
@Data
public class Roles {
    @Column(name = "user_id")
    @Id
    private String userId;
    @Id
    private String role;
}
