package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.HospitalResponse;
import com.mustache.bbs4.domain.entity.Hospital;
import com.mustache.bbs4.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository){
        this.hospitalRepository=hospitalRepository;
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){
        Optional<Hospital> hospital=hospitalRepository.findById(id);
        return ResponseEntity.ok().body(Hospital.of(hospital.get()));
    }
}
