package com.master.board.adapters.out.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.master.board.domain.models.enums.CardState;
import com.master.board.domain.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity(name = "card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column
        String name;
        @Column
        String description;
        @Column(name = "due_date")
        private Date dueDate;
        @Column(name = "reminder_date")
        private Date reminderDate;
        @Column(name = "is_active")
        private Boolean isActive;
        @Enumerated(EnumType.STRING)
        CardState state;
        @ManyToOne
        @JoinColumn(name = "case_type_id")
        CaseTypeEntity caseType;
        @Column(name = "created_at")
        @CreatedDate
        private Date createdAt;
        @Column(name = "updated_at")
        @LastModifiedDate
        private Date updatedAt;

}
