package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.entity.impl.CropEntity;

import java.util.List;

public interface CropService {
    public void saveCrop(CropDto dto);
    public void updateCrop(String CropId, CropDto dto);
    public void deleteCrop(String id);
    public CropDto getCrop(String id);
    public List<CropDto> getCropList();

    boolean existsById(String id);

    List<CropFieldDetailsDto> getCropFieldList(String cropCode,List<String> fields);

}
