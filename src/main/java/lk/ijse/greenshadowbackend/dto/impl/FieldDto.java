package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.entity.impl.CropFieldDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEquipmentDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffFieldEntityDetailsEntity;
import lombok.Data;

import java.util.List;
@Data
public class FieldDto {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropFieldDetailsDto> cropFieldDetailsDtos;
    private List<StaffFieldDetailsDto> staffFieldDetailsDtos;
    private List<FieldEquipmentDetailsDto> fieldEquipmentDetailsDtos;
    private List<FieldLogDetailsDto> fieldLogDetailsDtos;
}
