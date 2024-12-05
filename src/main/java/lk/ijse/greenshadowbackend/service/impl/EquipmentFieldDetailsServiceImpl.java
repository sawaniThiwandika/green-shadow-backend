package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.EquipmentFieldDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldEquipmentDetailsDto;
import lk.ijse.greenshadowbackend.entity.impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEquipmentDetailsEntity;
import lk.ijse.greenshadowbackend.service.EquipmentFieldDetailsService;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EquipmentFieldDetailsServiceImpl implements EquipmentFieldDetailsService {
    @Autowired
    Mapping mapping;
    @Autowired
    FieldService fieldService;
    @Autowired
    EquipmentFieldDetailsDao equipmentFieldDetailsDao;

    @Override
    public List<FieldEquipmentDetailsDto> getEquipmentFieldDtoList(List<String> fieldList, String eId) {
        ArrayList<FieldEquipmentDetailsDto> equipmentFieldDetailsDtos = new ArrayList<>();
        for (String fieldId : fieldList
        ) {
            equipmentFieldDetailsDtos.add(new FieldEquipmentDetailsDto(1, eId, fieldId));
        }
        return equipmentFieldDetailsDtos;
    }

    @Override
    public boolean saveEquipmentFieldDetails(EquipmentEntity equipmentEntity, List<FieldEquipmentDetailsDto> fieldEquipmentDetailsDtos) {
        boolean isSaved = true;

        for (FieldEquipmentDetailsDto dto : fieldEquipmentDetailsDtos) {
            try {
                System.out.println("Processing FieldId: " + dto.getFieldId());

                FieldDto field = fieldService.getField(dto.getFieldId());


                if (field == null) {
                    throw new IllegalArgumentException("Field not found for ID: " + dto.getFieldId());
                }
                FieldEntity fieldEntity = mapping.toFieldEntity(field);
                System.out.println("Mapped FieldEntity: " + fieldEntity);

                // Create and save FieldLogDetailsEntity
                FieldEquipmentDetailsEntity fieldLogDetailsEntity = new FieldEquipmentDetailsEntity();
                fieldLogDetailsEntity.setField(fieldEntity);
                fieldLogDetailsEntity.setEquipment(equipmentEntity);
                equipmentFieldDetailsDao.save(fieldLogDetailsEntity);

            } catch (Exception e) {
                System.out.println("Error while saving FieldEquipmentDetails for FieldId " + dto.getFieldId() + ": " + e.getMessage());
                e.printStackTrace();
                isSaved = false;
            }
        }

        return isSaved;
    }

}

