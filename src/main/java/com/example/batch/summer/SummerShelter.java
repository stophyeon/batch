package com.example.batch.summer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "summer_shelters",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"name", "address"})
        }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummerShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 DB용 PK

    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "shelter_name")
    private String shelterName;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(name = "facility_type")
    private String facilityType;

    @Column(name = "facility_sub_type")
    private String facilitySubType;

    @Column(name = "usable_people")
    private Integer usablePeople;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "weekday_start_time")
    private String weekdayStartTime;

    @Column(name = "weekday_end_time")
    private String weekdayEndTime;

    @Column(name = "weekend_start_time")
    private String weekendStartTime;

    @Column(name = "weekend_end_time")
    private String weekendEndTime;

    @Column(name = "weekend_open_yn")
    private String weekendOpenYn;

    @Column(name = "night_open_yn")
    private String nightOpenYn;

    @Column(name = "stay_possible_yn")
    private String stayPossibleYn;

    @Column(name = "cold_air_conditioner_count")
    private Integer coldAirConditionerCount;

    @Column(name = "electric_fan_count")
    private Integer electricFanCount;

    @Column(name = "area")
    private Integer area;

    @Column(name = "year")
    private String year;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "manager_dept_code")
    private String managerDeptCode;

    @Column(name = "modified_time")
    private String modifiedTime;

    @Column(name = "input_time")
    private String inputTime;

    @Column(name = "remark")
    private String remark;
}

