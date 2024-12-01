package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.LogCropDetailsDao;
import lk.ijse.greenshadowbackend.dao.LogDao;
import lk.ijse.greenshadowbackend.dao.LogFieldDetailsDao;
import lk.ijse.greenshadowbackend.dao.LogStaffDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.LogDto;
import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.CropFieldDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.service.LogCropDetailsService;
import lk.ijse.greenshadowbackend.service.LogFieldDetailsService;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.service.LogStaffDetailsService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    Mapping mapping;

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
    public void updateLog(String logId, LogDto dto) {
        Optional<LogEntity> optionalLog= logDao.findById(logId);
        if (optionalLog.isEmpty()) {
            throw new RuntimeException("Log with ID " + logId + " not found");
        }

        LogEntity logEntity = optionalLog.get();
        //cropEntity.setCropFieldDetails(cropFieldDetailsList);
        logEntity.setLogDate(dto.getLogDate());
        logEntity.setLogDetails(dto.getLogDetails());
        logEntity.setObservedImage(dto.getObservedImage());


        LogEntity save = logDao.save(logEntity);

        if (logDao.save(logEntity) == null){
            throw new RuntimeException("Log not update!!");
        }

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

        ArrayList<CropDto> logDtos = new ArrayList<>();
        List<LogEntity> logEntities = logDao.findAll();
        if(logEntities.isEmpty()){
            new RuntimeException("Failed to load");
        }

        return logEntities.stream()
                .map(mapping::toLogDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
