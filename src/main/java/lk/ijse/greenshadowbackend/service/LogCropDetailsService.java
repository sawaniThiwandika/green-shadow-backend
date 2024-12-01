package lk.ijse.greenshadowbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.CropLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LogCropDetailsService {
    public List<CropLogDetailsDto> getLogCropDtoList(List<String> cropList, String logId);
    public boolean saveLogCropDetails(LogEntity logEntity,List<CropLogDetailsDto>cropLogDetailsDtos);
}
