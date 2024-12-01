package lk.ijse.greenshadowbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LogFieldDetailsService {
    public List<FieldLogDetailsDto>getLogFieldDtoList(List<String> fieldList,String logId);
    public boolean saveLogFieldDetails(LogEntity logEntity,List<FieldLogDetailsDto> fieldLogDetailsDtos);
}

