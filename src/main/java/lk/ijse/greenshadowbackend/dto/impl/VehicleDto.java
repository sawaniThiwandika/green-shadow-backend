package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lombok.Data;

@Data

public class VehicleDto implements SuperDto {
    private String vehicleCode;
    private String vehicleLicensePlateNumber;
    private String vehicleCategory;
    private String vehicleFuelType;
    private String vehicleStatus;
    private String vehicleRemarks;
    private String staffId;
}
