package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ProductResquest;
import com.cybersoft.cozastore.payload.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getProductByCategoryId(String hostName, int id);
    boolean addProduct(ProductResquest productResquest);

    ProductResponse getDetailProduct(int id);

    boolean clearCache();

}
