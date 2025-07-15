package com.example.batch.winter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinterShelterDto {
    private String shelterId;          // 쉼터시설번호 (RSTR_FCLTY_NO)
    private int year;                 // 년도 (YEAR)
    private String regionCode;        // 지역코드 (ARCD)
    private String facilityType;      // 시설유형 중분류 (FCLTY_TY)
    private String name;              // 쉼터명칭 (RSTR_NM)
    private String address;           // 상세주소 (DTL_ADRES)
    private Integer area;             // 면적 (AR)
    private Integer capacity;         // 이용가능인원 (USE_PSBL_NMPR)
    private Integer fans;             // 선풍기 보유 수량 (COLR_HOLD_ELEFN)
    private Integer airConditioners;  // 에어컨 보유 수량 (COLR_HOLD_ARCNDTN)
    private String longitude;         // 경도 (LO)
    private String latitude;          // 위도 (LA)
    private String detailPosition;    // 위치상세 (DTL_POSITION)
    private String weekdayStartTime;  // 평일운영시작시간 (WKDAY_OPER_BEGIN_TIME)
    private String weekdayEndTime;    // 평일운영종료시간 (WKDAY_OPER_END_TIME)
    private String weekendStartTime;  // 주말운영시작시간 (WKEND_HDAY_OPER_BEGIN_TIME)
    private String weekendEndTime;    // 주말운영종료시간 (WKEND_HDAY_OPER_END_TIME)
    private String facilitySubType;   // 시설유형 소분류 (FCLTY_SCLAS)
}
