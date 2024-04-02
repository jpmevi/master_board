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

import java.util.Date;

@Data
@Entity(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column
        String comment;
        @ManyToOne
        @JoinColumn(name = "user_id")
        UserEntity user;
        @Column(name = "card_item_id")
        Long cardItemId;
        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        private Date createdAt;
        @LastModifiedDate
        @Column(name = "updated_at")
        private Date updatedAt;

}
