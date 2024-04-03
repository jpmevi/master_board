package com.master.board.adapters.out.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
@Entity(name = "case_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CaseTypeEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column
        String name;
        @Column
        String description;
        @Column("label_color")
        String labelColor;
        //timestamps
        @Column("created_at")
        @CreatedDate
        private Date createdAt;
        @Column("updated_at")
        @LastModifiedDate
        private Date updatedAt;

}
