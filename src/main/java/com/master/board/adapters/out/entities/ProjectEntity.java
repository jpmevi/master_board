package com.master.board.adapters.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        Long userId;
        String name;
        String description;
        String background_url;
        Boolean isPublic;
        Boolean isActive;
        @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
        private List<CaseTypeEntity> caseTypes;
}
