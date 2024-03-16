package com.master.board.adapters.out.mysqlJDBC.repositories;

import com.master.board.adapters.out.mysqlJDBC.entities.UserEntity;
import com.master.board.domain.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    @Query("select * from user where email =:email")
    Optional<UserEntity> findUserByEmail(String email);
}
