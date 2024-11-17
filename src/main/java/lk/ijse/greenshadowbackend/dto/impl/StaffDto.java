package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.impl.StaffCropDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffFieldEntityDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDto implements SuperDto {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String joinedDate;
    private String dob;
    private String contactNo;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String role;
    private List<StaffCropDetailsDto> cropStaffDetails;
    private List<StaffFieldDetailsDto> staffFieldDetails;
    private List<VehicleDto> vehicleDtoList;
    private List<StaffLogDetailsDto>staffLogDetails ;
}
