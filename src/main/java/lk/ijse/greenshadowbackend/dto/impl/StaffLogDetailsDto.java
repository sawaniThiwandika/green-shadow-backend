package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lombok.Data;

@Data
public class StaffLogDetailsDto implements SuperDto {
    private int id;
    private StaffEntity staffId;
    private LogEntity logId;
}
