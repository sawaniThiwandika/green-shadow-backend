package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lombok.Data;

@Data
public class StaffEquipmentDetailsDto implements SuperDto {
    private int id;
    private String staffId;
    private String equipmentId;
}
