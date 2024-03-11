package com.master.board.domain;

public record User(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String address,
        String phone,
        String imgUrl,
        String role

) {
}
