package com.takeaway.service.page;

import com.takeaway.model.page.PageResponse;
import org.springframework.stereotype.Service;

public interface PageService {

    PageResponse courseSearch(Integer page, Integer type);

    PageResponse orderList(Integer page, Long userId);

}
