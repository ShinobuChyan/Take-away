package com.takeaway.util;

import com.takeaway.model.page.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

    public static PageResponse getPageResponse(Page page) {
        PageResponse response = new PageResponse();
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setContent((page.getContent()));
        return response;
    }

}
