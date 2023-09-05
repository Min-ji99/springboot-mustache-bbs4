package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.entity.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    List<String> inClues;
    @BeforeEach
    void setup(){
        inClues=new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
    }
    @Test
    void name(){
        Optional<Hospital> opt=hospitalRepository.findById(1);
        Hospital hp=opt.get();
        assertEquals(1, hp.getId());
    }

    @Test
    void findByBusinessTypeNameIn(){
        List<Hospital> hospitals=hospitalRepository.findByBusinessTypeNameIn(inClues);
        for(var hospital : hospitals){
            System.out.println(hospital.getBusinessTypeName());
        }
    }

    @Test
    void findByRoadNameAddressContaining(Pageable pageable){
        Page<Hospital> hospitals=hospitalRepository.findByRoadNameAddressContaining("강남구", pageable);
        for(var hospital : hospitals){
            System.out.printf("%s %s\n", hospital.getHospitalName(), hospital.getRoadNameAddress());
        }
    }
    @Test
    void findByHospitalNameStartingWith(){
        List<Hospital> hospitals=hospitalRepository.findByHospitalNameStartingWith("연세");
        for(var hospital : hospitals){
            System.out.printf("%s %s\n", hospital.getHospitalName(), hospital.getRoadNameAddress());
        }
    }

    @Test
    void findByTotalNumberOfBedsBetween(){
        List<Hospital> hospitals=hospitalRepository.findByTotalNumberOfBedsBetween(10, 19);
        for(var hospital : hospitals){
            System.out.printf("%s %d\n", hospital.getHospitalName(), hospital.getTotalNumberOfBeds());
        }
    }

    @Test
    void findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(){
        List<Hospital> hospitals=hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(1, 3);
        for(var hospital : hospitals){
            System.out.printf("%s %d\n", hospital.getHospitalName(), hospital.getPatientRoomCount());
        }
    }
}