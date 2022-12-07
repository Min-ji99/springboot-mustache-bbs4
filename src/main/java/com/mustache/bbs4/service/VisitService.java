package com.mustache.bbs4.service;

import com.mustache.bbs4.domain.dto.VisitCreateRequest;
import com.mustache.bbs4.domain.dto.VisitCreateResponse;
import com.mustache.bbs4.domain.entity.Hospital;
import com.mustache.bbs4.domain.entity.User;
import com.mustache.bbs4.domain.entity.Visit;
import com.mustache.bbs4.exception.AppException;
import com.mustache.bbs4.exception.ErrorCode;
import com.mustache.bbs4.repository.HospitalRepository;
import com.mustache.bbs4.repository.UserRepository;
import com.mustache.bbs4.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public VisitService(VisitRepository visitRepository, HospitalRepository hospitalRepository, UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
    }

    public VisitCreateResponse createVisit(VisitCreateRequest visitCreateRequest, String username) {
        Hospital hospital=hospitalRepository.findById(visitCreateRequest.getHospitalId())
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND_HOSPITAL_ID, String.format("hospitalId %s가 존재하지 않습니다.", visitCreateRequest.getHospitalId())));
        User user=userRepository.findByUsername(username)
                        .orElseThrow(()->new AppException(ErrorCode.NOTFOUND_USER_NAME, String.format("Username %s이 존재하지 않습니다", username)));
        Visit visit = Visit.builder()
                        .hospital(hospital)
                        .user(user)
                        .disease(visitCreateRequest.getDisease())
                        .amount(visitCreateRequest.getAmount())
                        .build();
        visitRepository.save(visit);
        return new VisitCreateResponse("등록이 성공적으로 완료되었습니다.");
    }
}
