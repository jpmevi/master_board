package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query(
            value = "SELECT * FROM user u WHERE u.email = :email",
            nativeQuery = true)
    Optional<UserEntity> findUserByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM user u WHERE u.role = :roleName AND u.first_name LIKE %:userName%",
            nativeQuery = true)
    List<UserEntity> getUserByRoleAndName(@Param("roleName") String roleName, @Param("userName") String userName);
}
