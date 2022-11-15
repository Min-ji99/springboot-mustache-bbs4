package com.mustache.bbs4.domain.entity;

import com.mustache.bbs4.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "patient_room_count")
    private Integer patientRoomCount;

    @Column(name="total_number_of_beds")
    private Integer totalNumberOfBeds;

    @Column(name = "total_area_size")
    private Float totalAreaSize;

    @Column(name="business_type_name")
    private String businessTypeName;
    private Integer businessStatusCode;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }
}