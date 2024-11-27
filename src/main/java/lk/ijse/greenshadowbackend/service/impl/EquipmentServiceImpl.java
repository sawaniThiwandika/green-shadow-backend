package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowbackend.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Override
    public void saveEquipment(EquipmentDto dto) {

    }

    @Override
    public void updateEquipment(String EquipmentId, EquipmentDto dto) {

    }

    @Override
    public void deleteEquipment(String id) {

    }

    @Override
    public EquipmentDto getEquipment(String id) {
        return null;
    }

    @Override
    public List<EquipmentDto> getEquipmentList() {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
