package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.EquipmentDao;
import lk.ijse.greenshadowbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.service.EquipmentFieldDetailsService;
import lk.ijse.greenshadowbackend.service.EquipmentService;
import lk.ijse.greenshadowbackend.service.EquipmentStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentDao equipmentDao;
    @Autowired
    EquipmentStaffDetailsService equipmentStaffDetailsService;
    @Autowired
    EquipmentFieldDetailsService equipmentFieldDetailsService;

    @Override
    public void saveEquipment(EquipmentDto dto) {
        EquipmentEntity equipmentEntity = new EquipmentEntity();
        equipmentEntity.setEquipmentId(dto.getEquipmentId());
        equipmentEntity.setEquipmentName(dto.getEquipmentName());
        equipmentEntity.setEquipmentStatus(dto.getEquipmentStatus());
        equipmentEntity.setEquipmentType(dto.getEquipmentType());
        equipmentEntity.setStaffEquipmentDetails(new ArrayList<>());
        equipmentEntity.setFieldEquipmentDetails(new ArrayList<>());

        // System.out.println("Log Entity  in Log Service "+ logEntity);
        System.out.println("Before save " + equipmentEntity.getEquipmentId());
        equipmentDao.save(equipmentEntity);
        equipmentDao.flush(); // Ensure the entity is saved immediately

        // Save related entities
        boolean saveFieldDetails = equipmentFieldDetailsService.saveEquipmentFieldDetails(equipmentEntity,dto.getFieldEquipmentDetails());
        if (saveFieldDetails) {
                boolean saveStaffDetails = equipmentStaffDetailsService.saveEquipmentStaffDetails(equipmentEntity, dto.getStaffEquipmentDetails());
                if (!saveStaffDetails) {
                    System.out.println("Error in saving staff details");
                }
        } else {
            System.out.println("Error in saving field details");
        }

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
