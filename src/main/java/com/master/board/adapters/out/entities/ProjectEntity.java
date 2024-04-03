package com.master.board.adapters.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
        //timestamps
        @Column(name = "created_at")
        @CreatedDate
        private Date createdAt;
        @Column(name = "updated_at")
        @LastModifiedDate
        private Date updatedAt;

}
