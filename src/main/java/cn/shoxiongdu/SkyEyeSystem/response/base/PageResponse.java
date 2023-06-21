package cn.shoxiongdu.SkyEyeSystem.response.base;

import lombok.Data;

@Data
public class PageResponse {
    private int total;
    private int size;
    private int current;
    private int pages;
}
