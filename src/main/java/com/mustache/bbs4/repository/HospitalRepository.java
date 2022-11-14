package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String address);

    List<Hospital> findByHospitalNameStartingWith(String keyword);
    List<Hospital> findByTotalNumberOfBedsBetween(int start, int end);
}
