package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldEquipmentDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffEquipmentDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;

import java.util.List;

public interface EquipmentStaffDetailsService {
    public List<StaffEquipmentDetailsDto> getEquipmentStaffDtoList(List<String> staffList, String eID);
    public boolean saveEquipmentStaffDetails(EquipmentEntity equipmentEntity, List<StaffEquipmentDetailsDto> staffEquipmentDetailsDtos);
}
