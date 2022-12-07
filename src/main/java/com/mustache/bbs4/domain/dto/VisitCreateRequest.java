package com.mustache.bbs4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VisitCreateRequest {
    private Integer hospitalId;
    private String disease;
    private float amount;
}
