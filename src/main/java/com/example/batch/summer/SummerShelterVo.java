package com.example.batch.summer;


import lombok.Data;

import java.util.List;

@Data
public class SummerShelterVo {
    private Header header;

    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

    private List<SummerShelterDto> body;

    @Data
    public static class Header {
        private String resultMsg;
        private String resultCode;
        private String errorMsg;
    }

}
