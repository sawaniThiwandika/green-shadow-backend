package lk.ijse.greenshadowbackend.service;


import lk.ijse.greenshadowbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;

import java.util.List;

public interface EquipmentService {
    public void saveEquipment(EquipmentDto dto);
    public void updateEquipment(String EquipmentId, EquipmentDto dto);
    public void deleteEquipment(String id);
    public EquipmentDto getEquipment(String id);
    public List<EquipmentDto> getEquipmentList();

    boolean existsById(String id);
}
