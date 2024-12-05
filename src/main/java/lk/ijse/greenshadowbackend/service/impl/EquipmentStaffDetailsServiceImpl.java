package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.EquipmentStaffDetailsDao;
import lk.ijse.greenshadowbackend.dao.LogStaffDetailsDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffEquipmentDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEquipmentDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffLogDetailsEntity;
import lk.ijse.greenshadowbackend.service.EquipmentStaffDetailsService;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class EquipmentStaffDetailsServiceImpl implements EquipmentStaffDetailsService {
    @Autowired
    EquipmentStaffDetailsDao equipmentStaffDetailsDao;
    @Autowired
    Mapping mapping;
    @Autowired
    StaffDao staffDao;
    @Autowired
    StaffService staffService;
    @Override
    public List<StaffEquipmentDetailsDto> getEquipmentStaffDtoList(List<String> staffList, String eID) {
        ArrayList<StaffEquipmentDetailsDto> staffEquipmentDetailsDtos = new ArrayList<>();
        for (String staffId:staffList
        ) {
            staffEquipmentDetailsDtos.add(new StaffEquipmentDetailsDto(1,staffId,eID));
        }
        return staffEquipmentDetailsDtos;
    }

    @Override
    public boolean saveEquipmentStaffDetails(EquipmentEntity equipmentEntity, List<StaffEquipmentDetailsDto> staffEquipmentDetailsDtos) {
        boolean isSaved=true;
        for (StaffEquipmentDetailsDto dto:staffEquipmentDetailsDtos
        ) {
            try {
                StaffDto staff = staffService.getStaff(dto.getStaffId());
                StaffEquipmentDetailsEntity staffEquipmentDetailsEntity = new StaffEquipmentDetailsEntity();
                staffEquipmentDetailsEntity.setEquipment(equipmentEntity);
                System.out.println("staff entity in log " +mapping.toStaffEntity(staff));
                equipmentStaffDetailsDao.save(staffEquipmentDetailsEntity);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                isSaved=false;
            }
        }
        return isSaved;
    }
}
