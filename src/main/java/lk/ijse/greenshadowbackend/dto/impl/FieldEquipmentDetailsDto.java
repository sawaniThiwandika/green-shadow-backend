package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lombok.Data;

@Data
public class FieldEquipmentDetailsDto implements SuperDto {
    private int id;
    private String fieldId;
    private String equipmentId;
}
