package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lombok.Data;

@Data
public class CropFieldDetailsDto  implements SuperDto {
    private int id;
    private String cropCode;
    private String fieldId;
}
