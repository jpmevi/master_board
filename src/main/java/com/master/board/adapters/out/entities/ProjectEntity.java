package com.master.board.adapters.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "project")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        @OneToOne
        @JoinColumn(name = "user_id")
        UserEntity user;
        String name;
        String description;
        String background_url;
        Boolean isPublic;
        Boolean isActive;
        String disabled_reason;
        @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
        private List<CaseTypeEntity> caseTypes;
        //timestamps
        @org.springframework.data.relational.core.mapping.Column("created_at")
        @CreatedDate
        private Date createdAt;
        @Column("updated_at")
        @LastModifiedDate
        private Date updatedAt;

}
