package com.example.batch.winter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinterShelterDto {

    @JsonProperty("REARE_NM")
    private String name;  // 쉼터명

    @JsonProperty("SNDY_OPER_BGNG_HR")
    private String sundayOpenTime;

    @JsonProperty("MDFCN_HR")
    private String modifiedAt;  // 수정 일시 (yyyy-MM-dd HH:mm:ss.SSS)

    @JsonProperty("RONA_DADDR")
    private String roadAddress;

    @JsonProperty("LHLDY_OPER_END_HR")
    private String holidayCloseTime;

    @JsonProperty("DADDR")
    private String addressNumber;  // 지번

    @JsonProperty("UTZTN_PSBLTY_TNOP")
    private Integer capacity;  // 수용 가능 인원

    @JsonProperty("WKDY_OPER_BGNG_HR")
    private String weekdayOpenTime;

    @JsonProperty("STDY_OPER_END_HR")
    private String saturdayCloseTime;

    @JsonProperty("SNDY_OPER_END_HR")
    private String sundayCloseTime;

    @JsonProperty("FCLT_TYPE")
    private String facilityType;

    @JsonProperty("FCLTY_SCLAS")
    private String facilitySubType;

    @JsonProperty("REARE_FCLT_NO")
    private Long facilityId;

    @JsonProperty("LOT")
    private Double longitude;

    @JsonProperty("LAT")
    private Double latitude;

    @JsonProperty("STDY_OPER_BGNG_HR")
    private String saturdayOpenTime;

    @JsonProperty("RMRK")
    private String remark;

    @JsonProperty("LHLDY_OPER_BGNG_HR")
    private String holidayOpenTime;

    @JsonProperty("WKDY_OPER_END_HR")
    private String weekdayCloseTime;
}

