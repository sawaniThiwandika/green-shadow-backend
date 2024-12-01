package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.LogStaffDetailsDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffLogDetailsEntity;
import lk.ijse.greenshadowbackend.service.LogStaffDetailsService;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LogStaffDetailsServiceImpl implements LogStaffDetailsService {
    @Autowired
    LogStaffDetailsDao logStaffDetailsDao;
    @Autowired
    Mapping mapping;
    @Autowired
    StaffDao staffDao;
    @Autowired
    StaffService staffService;
    @Override
    public List<StaffLogDetailsDto> getLogStaffDtoList(List<String> staffList, String logId) {
        ArrayList<StaffLogDetailsDto> staffLogDetailsDtos = new ArrayList<>();
        for (String staffId:staffList
             ) {
            staffLogDetailsDtos.add(new StaffLogDetailsDto(1,staffId,logId));
        }
        return staffLogDetailsDtos;
    }

    @Override
    public boolean saveLogStaffDetails(LogEntity logEntity,List<StaffLogDetailsDto> staffLogDetailsDtos) {
        boolean isSaved=true;
        for (StaffLogDetailsDto dto:staffLogDetailsDtos
        ) {
            try {
                StaffDto staff = staffService.getStaff(dto.getStaffId());
                StaffLogDetailsEntity staffLogDetailsEntity =new StaffLogDetailsEntity();
                staffLogDetailsEntity.setStaff(mapping.toStaffEntity(staff));
                staffLogDetailsEntity.setLog(logEntity);
                staffLogDetailsEntity.setId(1);
                System.out.println("staff entity in log " +mapping.toStaffEntity(staff));
                logStaffDetailsDao.save(staffLogDetailsEntity);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                isSaved=false;
            }
        }
        return isSaved;
    }
}
