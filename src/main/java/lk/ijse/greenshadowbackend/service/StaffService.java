package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.StaffDto;

import java.util.List;

public interface StaffService {
    public void saveStaff(StaffDto dto);
    public void updateStaff(String staffId, StaffDto dto);
    public void deleteStaff(String id);
    public StaffDto getStaff(String id);
    public List<StaffDto> getStaffList();

    boolean existsById(String id);

}
