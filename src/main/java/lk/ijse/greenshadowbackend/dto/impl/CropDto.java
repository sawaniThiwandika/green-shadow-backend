package lk.ijse.greenshadowbackend.dto.impl;

import lombok.Data;

import java.util.List;

@Data
public class CropDto {
    private String code;
    private String name;
    private String scientificName;
    private String category;
    private String season;
    private String image;

    private List<CropFieldDetailsDto> cropFieldDetails;

    private List<StaffCropDetailsDto> cropStaffDetails;

    private List<CropLogDetailsDto> cropLogDetails;
}
