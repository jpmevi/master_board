package com.master.board.domain.models;

import com.master.board.domain.models.enums.Role;

import java.util.Collection;

public record User(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String password,
        String address,
        String phone,
        String imgUrl,
        Role role,
        Collection authorities

) {
}
