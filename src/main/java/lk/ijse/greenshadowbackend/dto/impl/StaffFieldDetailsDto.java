package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lombok.Data;

@Data
public class StaffFieldDetailsDto implements SuperDto {
    private int id;
    private String fieldId;
    private String staffId;
}
