package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.service.imp.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getAllColor() {
        List<ColorEntity> list = colorRepository.findAll();
        List<ColorResponse> responseList = new ArrayList<>();

        for (ColorEntity data:list) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(data.getId());
            colorResponse.setName(data.getName());

            responseList.add(colorResponse);
        }
        return responseList;
    }
}
