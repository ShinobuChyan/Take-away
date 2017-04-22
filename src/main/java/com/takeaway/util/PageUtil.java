package com.takeaway.util;

import com.takeaway.model.page.PageResponse;
import org.springframework.data.domain.Page;

public class PageUtil {

    public static <T> PageResponse getPageResponse(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setContent(page.getContent());
        return response;
    }

}
