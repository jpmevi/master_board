package com.master.board.adapters.out.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.master.board.domain.models.enums.CardItemState;
import com.master.board.domain.models.enums.CardState;
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
@Entity(name = "card_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CardItemEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column
        String hours;
        @Enumerated(EnumType.STRING)
        CardItemState state;
        @JsonIgnoreProperties("caseType")
        @ManyToOne
        @JoinColumn(name = "case_type_flow_id")
        CaseTypeFlowEntity caseTypeFlow;
        @JsonIgnoreProperties("caseType")
        @ManyToOne
        @JoinColumn(name = "card_id")
        CardEntity card;
        @Column(name = "created_at")
        @CreatedDate
        private Date createdAt;
        @Column(name = "updated_at")
        @LastModifiedDate
        private Date updatedAt;

}
