package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDto;

import java.util.List;

public interface VehicleService {
    public void saveVehicle(VehicleDto dto);
    public void updateVehicle(String VehicleId, VehicleDto dto);
    public void deleteVehicle(String id);
    public VehicleDto getVehicle(String id);
    public List<VehicleDto> getVehicleList();

    boolean existsById(String id);
}
