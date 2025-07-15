package com.example.batch.winter;

import lombok.Data;

import java.util.List;

@Data
public class WinterShelterVo {
    private Header header;

    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

    private List<WinterShelterDto> body;

    @Data
    public static class Header {
        private String resultMsg;
        private String resultCode;
        private String errorMsg;
    }

}
