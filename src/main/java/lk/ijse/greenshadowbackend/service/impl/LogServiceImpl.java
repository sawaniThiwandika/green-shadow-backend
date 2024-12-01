package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.LogCropDetailsDao;
import lk.ijse.greenshadowbackend.dao.LogDao;
import lk.ijse.greenshadowbackend.dao.LogFieldDetailsDao;
import lk.ijse.greenshadowbackend.dao.LogStaffDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.LogDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.service.LogCropDetailsService;
import lk.ijse.greenshadowbackend.service.LogFieldDetailsService;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.service.LogStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;
    @Autowired
    LogFieldDetailsService logFieldDetailsService;
    @Autowired
    LogCropDetailsService logCropDetailsService;
    @Autowired
    LogStaffDetailsService logStaffDetailsService;

    @Override
    public void saveLog(LogDto dto) {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogCode(dto.getLogCode());
        logEntity.setLogDetails(dto.getLogDetails());
        logEntity.setObservedImage(dto.getObservedImage());
        logEntity.setLogDate(dto.getLogDate());
        logEntity.setCropLogDetailsEntities(new ArrayList<>());
        logEntity.setFieldLogDetailsEntities(new ArrayList<>());
        logEntity.setStaffLogDetailsEntities(new ArrayList<>());
       // System.out.println("Log Entity  in Log Service "+ logEntity);
        System.out.println("Before save " + logEntity.getLogCode());
        logDao.save(logEntity);
        logDao.flush(); // Ensure the entity is saved immediately

        // Save related entities
        boolean saveLogFieldDetails = logFieldDetailsService.saveLogFieldDetails(logEntity, dto.getFieldLogDetails());
        if (saveLogFieldDetails) {
            boolean saveLogCropDetails = logCropDetailsService.saveLogCropDetails(logEntity, dto.getCropLogDetails());
            if (saveLogCropDetails) {
                boolean saveLogStaffDetails = logStaffDetailsService.saveLogStaffDetails(logEntity, dto.getStaffLogDetails());
                if (!saveLogStaffDetails) {
                    System.out.println("Error in saving staff details");
                }
            } else {
                System.out.println("Error in saving crop details");
            }
        } else {
            System.out.println("Error in saving field details");
        }

    }

    @Override
    public void updateLog(String LogId, LogDto dto) {

    }

    @Override
    public void deleteLog(String id) {

    }

    @Override
    public LogDto getLog(String id) {
        return null;
    }

    @Override
    public List<LogDto> getLogList() {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
