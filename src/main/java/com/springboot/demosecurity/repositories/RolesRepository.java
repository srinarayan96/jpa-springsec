package com.springboot.demosecurity.repositories;

import com.springboot.demosecurity.entity.Roles;
import com.springboot.demosecurity.entity.Roles_PK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Roles_PK> {
    List<Roles> findByUserId(String userId);
}
