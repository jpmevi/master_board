package com.master.board.domain.models;

import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.domain.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

public record User(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String address,
        String phone,
        String imgUrl,
        Role role,
        Collection authorities

) {
    public User(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getAddress(),
                userEntity.getPhone(),
                userEntity.getImgUrl(),
                userEntity.getRole(),
                userEntity.getAuthorities()
        );
    }
    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String address() {
        return address;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String imgUrl() {
        return imgUrl;
    }

    @Override
    public Role role() {
        return role;
    }

    @Override
    public Collection authorities() {
        return authorities;
    }
}
