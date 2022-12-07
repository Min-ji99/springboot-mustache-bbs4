package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.VisitCreateRequest;
import com.mustache.bbs4.domain.dto.VisitCreateResponse;
import com.mustache.bbs4.domain.dto.VisitResponse;
import com.mustache.bbs4.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("")
    public ResponseEntity<List<VisitResponse>> getList(){
        List<VisitResponse> visits=visitService.getList();
        return ResponseEntity.ok().body(visits);

    }
}
