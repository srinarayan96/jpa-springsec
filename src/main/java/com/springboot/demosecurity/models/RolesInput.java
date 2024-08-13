package com.springboot.demosecurity.models;

import lombok.Data;

import java.util.List;

@Data
public class RolesInput {
    private List<String> roles;
}
