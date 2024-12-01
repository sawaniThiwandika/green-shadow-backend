package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.LogFieldDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldLogDetailsEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.service.LogFieldDetailsService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class LogFieldDetailsServiceImpl implements LogFieldDetailsService {
    @Autowired
    LogFieldDetailsDao logFieldDetailsDao;
    @Autowired
    Mapping mapping;
    @Autowired
    FieldService fieldService;

    @Override
    public List<FieldLogDetailsDto> getLogFieldDtoList(List<String> fieldList, String logId) {
        ArrayList<FieldLogDetailsDto> logFieldDetailsDtos = new ArrayList<>();
        for (String fieldId:fieldList
             ) {
            logFieldDetailsDtos.add(new FieldLogDetailsDto(1,logId,fieldId));
        }
        return logFieldDetailsDtos;
    }

    @Override
    public boolean saveLogFieldDetails(LogEntity logEntity,List<FieldLogDetailsDto> fieldLogDetailsDtos) {
        boolean isSaved = true;

        for (FieldLogDetailsDto dto : fieldLogDetailsDtos) {
            try {
                System.out.println("Processing FieldId: " + dto.getFieldId());

                // Fetch and map FieldEntity
                FieldDto field = fieldService.getField(dto.getFieldId());


                if (field == null) {
                    throw new IllegalArgumentException("Field not found for ID: " + dto.getFieldId());
                }
                FieldEntity fieldEntity = mapping.toFieldEntity(field);
                System.out.println("Mapped FieldEntity: " + fieldEntity);

                // Create and save FieldLogDetailsEntity
                FieldLogDetailsEntity fieldLogDetailsEntity = new FieldLogDetailsEntity();
                fieldLogDetailsEntity.setField(fieldEntity);
                fieldLogDetailsEntity.setLog(logEntity);
                logFieldDetailsDao.save(fieldLogDetailsEntity);

            } catch (Exception e) {
                System.out.println("Error while saving FieldLogDetails for FieldId " + dto.getFieldId() + ": " + e.getMessage());
                e.printStackTrace();
                isSaved = false;
            }
        }

        return isSaved;
    }

}
