package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.CropDao;
import lk.ijse.greenshadowbackend.dao.CropFieldDetailsDao;
import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.CropFieldDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.service.CropFieldDetailsService;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CropFieldDetailsImpl implements CropFieldDetailsService {

    @Autowired
    private CropFieldDetailsDao cropFieldDetailsDao;
    @Autowired
    private CropDao cropDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;
    @Override
    public List<CropFieldDetailsEntity> getCropFieldDetailsList(List<CropFieldDetailsDto> cropFieldDetailsDtoList) {
        ArrayList<CropFieldDetailsEntity> fieldsOfCropEntities = new ArrayList<>();
        for (CropFieldDetailsDto cropFieldDto:cropFieldDetailsDtoList) {
            Optional<CropEntity> cropById = cropDao.findById(cropFieldDto.getCropCode());
            Optional<FieldEntity> fieldById = fieldDao.findById(cropFieldDto.getFieldCode());
            CropFieldDetailsEntity cropFieldDetailsEntity = new CropFieldDetailsEntity();
            cropFieldDetailsEntity.setCrop(cropById.get());
            cropFieldDetailsEntity.setField(fieldById.get());
            //cropFieldDetailsEntity.setId(2);
            fieldsOfCropEntities.add(cropFieldDetailsEntity);

            System.out.println("Im in entity create method :"+ cropFieldDetailsEntity);
        }
        return fieldsOfCropEntities;
    }
}
