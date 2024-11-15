package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lombok.Data;

@Data
public class CropFieldDetailsDto {
    private int id;
    private String cropCode;
    private String fieldId;
}
