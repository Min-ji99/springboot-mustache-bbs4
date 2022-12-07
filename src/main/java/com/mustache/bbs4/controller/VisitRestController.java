package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.VisitCreateRequest;
import com.mustache.bbs4.domain.dto.VisitCreateResponse;
import com.mustache.bbs4.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitRestController {
    private final VisitService visitService;

    public VisitRestController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/create")
    public ResponseEntity<VisitCreateResponse> create(@RequestBody VisitCreateRequest visitCreateRequest){
        VisitCreateResponse visitCreateResponse=visitService.createVisit(visitCreateRequest, "minji");
        return ResponseEntity.ok().body(visitCreateResponse);
    }
}
