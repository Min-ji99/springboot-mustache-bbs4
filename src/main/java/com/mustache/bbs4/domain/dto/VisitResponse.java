package com.mustache.bbs4.domain.dto;

import com.mustache.bbs4.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VisitResponse {
    private Long id;
    private String hospitalName;
    private String username;
    private String disease;
    private float amount;
    public static VisitResponse of(Visit visit){
        return VisitResponse.builder()
                .id(visit.getId())
                .hospitalName(visit.getHospital().getHospitalName())
                .username(visit.getUser().getUsername())
                .disease(visit.getDisease())
                .amount(visit.getAmount())
                .build();
    }
}
