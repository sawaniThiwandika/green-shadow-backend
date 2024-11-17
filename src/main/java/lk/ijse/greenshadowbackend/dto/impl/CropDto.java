package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDto  implements SuperDto {
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
