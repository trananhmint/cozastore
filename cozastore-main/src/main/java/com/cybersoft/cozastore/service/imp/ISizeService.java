package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.response.SizeResponse;

import java.util.List;

public interface ISizeService {
    List<SizeResponse> getAllSize();
}
