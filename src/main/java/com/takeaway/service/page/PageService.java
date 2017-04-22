package com.takeaway.service.page;

import com.takeaway.model.page.PageResponse;
import org.springframework.stereotype.Service;

public interface PageService {

    PageResponse courseSearch(Integer page, Integer size, Integer type);

}
