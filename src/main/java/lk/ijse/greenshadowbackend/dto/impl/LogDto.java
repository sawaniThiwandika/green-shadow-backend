package lk.ijse.greenshadowbackend.dto.impl;


import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.CropLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffLogDetailsEntity;
import lombok.Data;

import java.util.List;
@Data
public class LogDto implements SuperDto {
    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    private List<StaffLogDetailsDto> staffLogDetails;
    private List<CropLogDetailsDto> cropLogDetails;
    private List<FieldLogDetailsDto>fieldLogDetails;
}
