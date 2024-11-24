package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.CropDao;
import lk.ijse.greenshadowbackend.dao.CropFieldDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.CropFieldDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.service.CropFieldDetailsService;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private CropFieldDetailsService cropFieldDetailsService;
    @Autowired
    private CropFieldDetailsDao cropFieldDetailsDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCrop(CropDto dto) {

        System.out.println("IM in save crop in service"+ dto);
        System.out.println("IM in save crop in service"+ dto.getCropFieldDetails());



        CropEntity cropEntity = new CropEntity();
        //cropEntity.setCropFieldDetails(cropFieldDetailsList);
        cropEntity.setCode(dto.getCode());
        cropEntity.setImage(dto.getImage());
        cropEntity.setCategory(dto.getCategory());
        cropEntity.setName(dto.getName());
        cropEntity.setSeason(dto.getSeason());
        cropEntity.setScientificName(dto.getScientificName());

        CropEntity save = cropDao.save(cropEntity);
        List<CropFieldDetailsEntity> cropFieldDetailsList = cropFieldDetailsService.getCropFieldDetailsList(dto.getCropFieldDetails());
        System.out.println("IM in save crop in service CRop Field Entity list"+ cropFieldDetailsList);
        for (CropFieldDetailsEntity cropFieldDetail : cropFieldDetailsList) {
            try {
                CropFieldDetailsEntity saveDetail = cropFieldDetailsDao.save(cropFieldDetail);
            } catch (Exception e) {
                new Exception("can not save crop field details");
            }


        }
        if (save == null){
            throw new RuntimeException("Crop not saved!!");
        }

    }

    @Override
    public void updateCrop(String cropId, CropDto dto) {
        Optional<CropEntity> optionalCrop = cropDao.findById(cropId);
        if (optionalCrop.isEmpty()) {
            throw new RuntimeException("Crop with ID " + cropId + " not found");
        }

        CropEntity cropEntity = optionalCrop.get();
        //cropEntity.setCropFieldDetails(cropFieldDetailsList);
        cropEntity.setImage(dto.getImage());
        cropEntity.setCategory(dto.getCategory());
        cropEntity.setName(dto.getName());
        cropEntity.setSeason(dto.getSeason());
        cropEntity.setScientificName(dto.getScientificName());
        List<CropFieldDetailsEntity> cropFieldDetailsList = cropFieldDetailsService.getCropFieldDetailsList(dto.getCropFieldDetails());
        System.out.println("IM in update crop in service CRop Field Entity list"+ cropFieldDetailsList);
        cropEntity.setCropFieldDetails(cropFieldDetailsList);
        CropEntity save = cropDao.save(cropEntity);

        if (save == null){
            throw new RuntimeException("Crop not saved!!");
        }
    }

    @Override
    public void deleteCrop(String id) {

    }

    @Override
    public CropDto getCrop(String id) {
        return null;
    }

    @Override
    public List<CropDto> getCropList() {
        ArrayList<CropDto> cropDtos = new ArrayList<>();
        List<CropEntity> cropEntities = cropDao.findAll();
        if(cropEntities.isEmpty()){
            new RuntimeException("Failed to load");
        }

        return cropEntities.stream()
                .map(mapping::toCropDto)
                .collect(Collectors.toList());

    }

    @Override
    public boolean existsById(String id) {
        return false;
    }


    @Override
    public List<CropFieldDetailsDto> getCropFieldList(String cropCode,List<String> fields) {
        ArrayList<CropFieldDetailsDto> fieldsOfCrop = new ArrayList<>();
        for (String fieldId:fields
             ) {
            System.out.println("fieldId: "+fieldId);
            System.out.println("cropId: "+cropCode);

            fieldsOfCrop.add(new CropFieldDetailsDto(1,cropCode,fieldId));
        }
        return fieldsOfCrop;
    }

}
