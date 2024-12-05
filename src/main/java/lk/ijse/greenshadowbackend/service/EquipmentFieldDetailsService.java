package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldEquipmentDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EquipmentFieldDetailsService {
    public List<FieldEquipmentDetailsDto> getEquipmentFieldDtoList(List<String> fieldList, String eId);
    public boolean saveEquipmentFieldDetails(EquipmentEntity equipmentEntity, List<FieldEquipmentDetailsDto> fieldEquipmentDetailsDtos);
}
