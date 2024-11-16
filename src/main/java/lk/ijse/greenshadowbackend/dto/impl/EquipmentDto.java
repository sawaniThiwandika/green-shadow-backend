package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEquipmentDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEquipmentDetailsEntity;
import lombok.Data;

import java.util.List;
@Data
public class EquipmentDto implements SuperDto {
    private String equipmentId;
    private String equipmentName ;
    private String equipmentType;
    private String equipmentStatus;
    private List<FieldEquipmentDetailsDto> fieldEquipmentDetails;
    private List<StaffEquipmentDetailsDto> staffEquipmentDetails;
}
