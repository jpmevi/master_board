package com.master.board.adapters.out.entities;

import com.master.board.domain.models.enums.CardState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity(name = "card_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardUserEntity {
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
}
