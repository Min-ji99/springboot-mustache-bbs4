package com.mustache.bbs4.service;

import com.mustache.bbs4.domain.dto.HospitalResponse;
import com.mustache.bbs4.domain.entity.Hospital;
import com.mustache.bbs4.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class HospitalServiceTest {
    private HospitalRepository hospitalRepository= Mockito.mock(HospitalRepository.class);
    private HospitalService hospitalService;

    @BeforeEach
    void setup(){
        hospitalService=new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("business Status code 폐업 확인")
    void closeBusinessStatusCode(){
        Integer id=1;
        Hospital hospital=Hospital.builder()
                .id(1)
                .businessStatusCode(3)
                .build();
        given(hospitalRepository.findById(id))
                .willReturn(Optional.of(hospital));
        HospitalResponse hospitalResponse=hospitalService.getHospital(id);
        assertEquals("폐업", hospitalResponse.getBusinessStatusName());
    }
    @Test
    @DisplayName("business Status code 영업중 확인")
    void openBusinessStatusCode(){
        Integer id=2;
        Hospital hospital=Hospital.builder()
                .id(2)
                .businessStatusCode(13)
                .build();
        given(hospitalRepository.findById(id))
                .willReturn(Optional.of(hospital));
        HospitalResponse hospitalResponse=hospitalService.getHospital(id);
        assertEquals("영업중", hospitalResponse.getBusinessStatusName());
    }
}