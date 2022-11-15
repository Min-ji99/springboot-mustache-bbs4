package com.mustache.bbs4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponse {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String businessStatusName;

    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id=id;
        this.roadNameAddress=roadNameAddress;
        this.hospitalName=hospitalName;
        this.patientRoomCount=patientRoomCount;
        this.totalNumberOfBeds=totalNumberOfBeds;
        this.totalAreaSize=totalAreaSize;
        this.businessTypeName=businessTypeName;
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

}
