package com.master.board.adapters.out.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity(name = "case_type_flow")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseTypeFlowEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        String stage;
        @Column(name = "`order`")
        Integer order;
        @ManyToOne
        @JoinColumn(name = "case_type_id")
        CaseTypeEntity caseType;
}
