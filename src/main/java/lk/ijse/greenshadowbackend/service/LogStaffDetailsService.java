package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffLogDetailsDto;

import java.util.List;

public interface LogStaffDetailsService {
    public List<StaffLogDetailsDto> getLogStaffDtoList(List<String> staffList, String logId);
}
