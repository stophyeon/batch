package com.example.batch.winter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shelters",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"name", "address"})
        },
        indexes = {
            @Index(name = "idx_region_code", columnList = "regionCode")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinterShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 DB용 PK

    @Column(name = "shelter_id")
    private String shelterId; // 공공데이터의 쉼터시설번호

    private int year;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "facility_type")
    private String facilityType;

    private String name;

    private String address;

    private Integer area;

    private Integer capacity;

    private Integer fans;

    @Column(name = "air_conditioners")
    private Integer airConditioners;

    private String longitude;

    private String latitude;

    @Column(name = "detail_position")
    private String detailPosition;

    @Column(name = "weekday_start_time")
    private String weekdayStartTime;

    @Column(name = "weekday_end_time")
    private String weekdayEndTime;

    @Column(name = "weekend_start_time")
    private String weekendStartTime;

    @Column(name = "weekend_end_time")
    private String weekendEndTime;

    @Column(name = "facility_subtype")
    private String facilitySubType;
}

