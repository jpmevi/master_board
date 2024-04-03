package com.master.board.adapters.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity(name = "card_activity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardActivityEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @ManyToOne
        @JoinColumn(name = "user_id")
        UserEntity user;
        @ManyToOne
        @JoinColumn(name = "card_id")
        CardEntity card;
        @ManyToOne
        @JoinColumn(name = "card_item_id",nullable = true)
        CardItemEntity cardItem;
        @Column
        String activity;
        @Column(name = "created_at")
        @CreatedDate
        private Date createdAt;
}
