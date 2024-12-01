package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.LogCropDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.CropLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.CropLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.service.LogCropDetailsService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class LogCropDetailsServiceImpl implements LogCropDetailsService {
    @Autowired
    Mapping mapping;
    @Autowired
    LogCropDetailsDao logCropDetailsDao;
    @Autowired
    CropService cropService;

    @Override
    public List<CropLogDetailsDto> getLogCropDtoList(List<String> cropList, String logId) {
        ArrayList<CropLogDetailsDto> cropLogDetailsDtos = new ArrayList<>();
        for (String cropCode:cropList
             ) {
            cropLogDetailsDtos.add(new CropLogDetailsDto(1,cropCode,logId));
        }
        return cropLogDetailsDtos;

    }
    @Override
    public boolean saveLogCropDetails(LogEntity logEntity,List<CropLogDetailsDto>cropLogDetailsDtos) {
        boolean isSaved=true;
        for (CropLogDetailsDto dto:cropLogDetailsDtos
        ) {
            try {
                CropLogDetailsEntity cropLogDetailsEntity = new CropLogDetailsEntity();
                cropLogDetailsEntity.setCrop(mapping.toCropEntity(cropService.getCrop(dto.getCropCode())));
                System.out.println("crop entity in log :"+mapping.toCropEntity(cropService.getCrop(dto.getCropCode())) );
                cropLogDetailsEntity.setLog(logEntity);
                cropLogDetailsEntity.setId(1);
                logCropDetailsDao.save(cropLogDetailsEntity);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                isSaved=false;
            }
        }
        return isSaved;
    }
}
