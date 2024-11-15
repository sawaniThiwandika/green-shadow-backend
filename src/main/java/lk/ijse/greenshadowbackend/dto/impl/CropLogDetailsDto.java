package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lombok.Data;

@Data
public class CropLogDetailsDto {
    private int id;
    private String cropCode;
    private String logId;

}
