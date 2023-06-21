package cn.shoxiongdu.SkyEyeSystem.request.base;

import lombok.Data;

@Data
public class PageRequest {
    private int pageNumber = 1;
    private int pageSize = 10;

    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public int getPageNumber() {
        return pageNumber == 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    public int getPageSize() {
        return pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
