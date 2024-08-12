package com.springboot.demosecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@IdClass(Roles_PK.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Column(name = "user_id")
    @Id
    private String userId;
    @Id
    private String role;
}
