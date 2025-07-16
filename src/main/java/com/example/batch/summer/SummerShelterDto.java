package com.example.batch.summer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummerShelterDto {
    @JsonProperty("RSTR_FCLTY_NO")
    private Long facilityId;

    @JsonProperty("RSTR_NM")
    private String shelterName;

    @JsonProperty("RN_DTL_ADRES")
    private String roadAddress;

    @JsonProperty("DTL_ADRES")
    private String addressNumber;

    @JsonProperty("FCLTY_TY")
    private String facilityType;

    @JsonProperty("FCLTY_SCLAS")
    private String facilitySubType;

    @JsonProperty("USE_PSBL_NMPR")
    private Integer usablePeople;

    @JsonProperty("LO")
    private Double longitude;

    @JsonProperty("LA")
    private Double latitude;

    @JsonProperty("WKDAY_OPER_BEGIN_TIME")
    private String weekdayStartTime;

    @JsonProperty("WKDAY_OPER_END_TIME")
    private String weekdayEndTime;

    @JsonProperty("WKEND_HDAY_OPER_BEGIN_TIME")
    private String weekendStartTime;

    @JsonProperty("WKEND_HDAY_OPER_END_TIME")
    private String weekendEndTime;

    @JsonProperty("CHCK_MATTER_WKEND_HDAY_OPN_AT")
    private String weekendOpenYn;

    @JsonProperty("CHCK_MATTER_NIGHT_OPN_AT")
    private String nightOpenYn;

    @JsonProperty("CHCK_MATTER_STAYNG_PSBL_AT")
    private String stayPossibleYn;

    @JsonProperty("COLR_HOLD_ARCNDTN")
    private Integer coldAirConditionerCount;

    @JsonProperty("COLR_HOLD_ELEFN")
    private Integer electricFanCount;

    @JsonProperty("AR")
    private Integer area;

    @JsonProperty("YEAR")
    private String year;

    @JsonProperty("ARCD")
    private String areaCode;

    @JsonProperty("MNGDPT_CD")
    private String managerDeptCode;

    @JsonProperty("MODF_TIME")
    private String modifiedTime;

    @JsonProperty("INPT_TIME")
    private String inputTime;

    @JsonProperty("RM")
    private String remark;
}
