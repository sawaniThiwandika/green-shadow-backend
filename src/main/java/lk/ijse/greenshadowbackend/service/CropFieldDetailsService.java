package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.CropFieldDetailsEntity;

import java.util.List;

public interface CropFieldDetailsService {
    public List<CropFieldDetailsEntity> getCropFieldDetailsList(List<CropFieldDetailsDto>cropFieldDetailsDtoList);
}
