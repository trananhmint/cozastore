package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ICategoryService {
    List<CategoryResponse> getAllCategory();
}
