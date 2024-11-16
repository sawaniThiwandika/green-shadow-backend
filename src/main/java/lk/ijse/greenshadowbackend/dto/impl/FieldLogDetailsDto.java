package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lombok.Data;

@Data
public class FieldLogDetailsDto  implements SuperDto {
    private int id;
    private String logId;
    private String fieldId;
}
