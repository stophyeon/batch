package com.example.batch.winter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "winter_shelters", indexes = {
        @Index(name = "idx_facility_id", columnList = "facilityId")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinterShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 DB용 기본키

    private String name;               // 쉼터명

    private String roadAddress;        // 도로명 주소
    private String addressNumber;      // 지번 주소

    private String facilityType;       // 시설 유형 (FCLT_TYPE)
    private String facilitySubType;    // 시설 소분류 (FCLTY_SCLAS)

    private Long facilityId;           // 시설 ID (REARE_FCLT_NO)

    private Integer capacity;          // 수용 가능 인원 (UTZTN_PSBLTY_TNOP)

    private String weekdayOpenTime;    // 평일 운영 시작
    private String weekdayCloseTime;   // 평일 운영 종료

    private String saturdayOpenTime;   // 토요일 운영 시작
    private String saturdayCloseTime;  // 토요일 운영 종료

    private String sundayOpenTime;     // 일요일 운영 시작
    private String sundayCloseTime;    // 일요일 운영 종료

    private String holidayOpenTime;    // 공휴일 운영 시작
    private String holidayCloseTime;   // 공휴일 운영 종료

    private String modifiedAt;         // 수정일시 (문자열 그대로 저장)

    private String remark;             // 비고 (nullable)

    private Double latitude;           // 위도
    private Double longitude;          // 경도
}


