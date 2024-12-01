package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;

import java.util.List;

public interface LogStaffDetailsService {
    public List<StaffLogDetailsDto> getLogStaffDtoList(List<String> staffList, String logId);
    public boolean saveLogStaffDetails(LogEntity logEntity,List<StaffLogDetailsDto> staffLogDetailsDtos);
}
