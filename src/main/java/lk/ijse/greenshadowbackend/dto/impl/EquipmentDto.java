package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEquipmentDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEquipmentDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto implements SuperDto {
    private String equipmentId;
    private String equipmentName ;
    private String equipmentType;
    private String equipmentStatus;
    private List<FieldEquipmentDetailsDto> fieldEquipmentDetails;
    private List<StaffEquipmentDetailsDto> staffEquipmentDetails;
}
