package com.master.board.adapters.out.mysqlJDBC.entities;

import com.master.board.domain.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class ProjectEntity {
        @Id
        Integer id;
        Long userId;
        String name;
        String description;
        String background_url;
        Boolean isPublic;
        Boolean isActive;
}
