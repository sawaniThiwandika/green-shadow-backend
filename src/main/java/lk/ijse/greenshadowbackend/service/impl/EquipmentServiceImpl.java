package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.EquipmentDao;
import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.service.EquipmentFieldDetailsService;
import lk.ijse.greenshadowbackend.service.EquipmentService;
import lk.ijse.greenshadowbackend.service.EquipmentStaffDetailsService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentDao equipmentDao;
    @Autowired
    EquipmentStaffDetailsService equipmentStaffDetailsService;
    @Autowired
    EquipmentFieldDetailsService equipmentFieldDetailsService;
    @Autowired
    Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDto dto) {
        EquipmentEntity equipmentEntity = new EquipmentEntity();
        equipmentEntity.setEquipmentId(dto.getEquipmentId());
        equipmentEntity.setEquipmentName(dto.getEquipmentName());
        equipmentEntity.setEquipmentStatus(dto.getEquipmentStatus());
        equipmentEntity.setEquipmentType(dto.getEquipmentType());
        equipmentEntity.setStaffEquipmentDetails(new ArrayList<>());
        equipmentEntity.setFieldEquipmentDetails(new ArrayList<>());


        System.out.println("Before save " + equipmentEntity.getEquipmentId());
        equipmentDao.save(equipmentEntity);
        equipmentDao.flush();

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
    public void updateEquipment(String equipmentId, EquipmentDto dto) {
        Optional<EquipmentEntity> byId = equipmentDao.findById(equipmentId);
        if (byId.isEmpty()) {
            throw new RuntimeException("Log with ID " + equipmentId + " not found");
        }

        EquipmentEntity equipmentEntity = byId.get();
        equipmentEntity.setEquipmentName(dto.getEquipmentName());
        equipmentEntity.setEquipmentStatus(dto.getEquipmentStatus());
        equipmentEntity.setEquipmentType(dto.getEquipmentType());
        equipmentEntity.setEquipmentType(dto.getEquipmentType());

        EquipmentEntity save = equipmentDao.save(equipmentEntity);

        if (save == null){
            throw new RuntimeException("Log not update!!");
        }
    }

    @Override
    public void deleteEquipment(String id) {
        System.out.println("EquipmentId "+id);
        Optional<EquipmentEntity> equipment = equipmentDao.findById(id);

        if (!equipment.isPresent()) {
            throw new  RuntimeException("Equipment with code " + id + " not found");
        }

        equipmentDao.deleteById(id);
    }

    @Override
    public EquipmentDto getEquipment(String id) {
        return null;
    }

    @Override
    public List<EquipmentDto> getEquipmentList() {

        List<EquipmentEntity> allEntities = equipmentDao.findAll();
        if(allEntities.isEmpty()){
            new RuntimeException("Failed to load");
        }

        return allEntities.stream()
                .map(mapping::toEquipmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
