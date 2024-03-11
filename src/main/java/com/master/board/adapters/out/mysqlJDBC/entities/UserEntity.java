package com.master.board.adapters.out.mysqlJDBC.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "user")
public record UserEntity(
        @Id
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String address,
        String phone,
        String imgUrl,
        String role,
        @Version
        Integer version
) {
}
