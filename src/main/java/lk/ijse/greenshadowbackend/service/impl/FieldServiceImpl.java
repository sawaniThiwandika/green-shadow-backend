package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.entity.impl.VehicleEntity;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDto dto) {
        FieldEntity save = fieldDao.save(mapping.toFieldEntity(dto));
        if (save == null){
            throw new RuntimeException("Note not saved!!");
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDto dto) {
        Optional<FieldEntity> optionalField = fieldDao.findById(fieldCode);
        if (optionalField.isEmpty()) {
            throw new RuntimeException("Staff with ID " + fieldCode + " not found");
        }

        FieldEntity fieldEntity = optionalField.get();

        fieldEntity.setFieldLocation(dto.getFieldLocation());
        fieldEntity.setFieldImage1(dto.getFieldImage1());
        fieldEntity.setFieldImage2(dto.getFieldImage2());
        fieldEntity.setFieldSize(dto.getFieldSize());
        fieldEntity.setFieldName(dto.getFieldName());

        fieldDao.save(fieldEntity);
    }

    @Override
    public void deleteField(String fieldCode) {
        System.out.println("Field code "+fieldCode);
        Optional<FieldEntity> field= fieldDao.findById(fieldCode);

        if (!field.isPresent()) {
            throw new  RuntimeException("Field with code " + fieldCode + " not found");
        }

        fieldDao.deleteById(fieldCode);
    }

    @Override
    public FieldDto getField(String id) {
        Optional<FieldEntity> byId = fieldDao.findById(id);

        // Check if the field exists and map it to FieldDto if present
        return byId.map(this::mapToFieldDto) // Convert FieldEntity to FieldDto
                .orElseThrow(() -> new NoSuchElementException("Field not found for ID: " + id));
    }

    // Utility method to map FieldEntity to FieldDto
    private FieldDto mapToFieldDto(FieldEntity fieldEntity) {
        FieldDto fieldDto = new FieldDto();
        fieldDto.setFieldCode(fieldEntity.getFieldCode());
        fieldDto.setFieldName(fieldEntity.getFieldName());
        fieldDto.setFieldLocation(fieldEntity.getFieldLocation());
        fieldDto.setFieldSize(fieldEntity.getFieldSize());
        fieldDto.setFieldImage1(fieldEntity.getFieldImage1());
        fieldDto.setFieldImage2(fieldEntity.getFieldImage2());
        return fieldDto;
    }

    @Override
    public List<FieldDto> getFieldList() {
        List<FieldEntity> all = fieldDao.findAll();
        if(all.isEmpty()){
            new RuntimeException("Failed to load");
        }
        return all.stream()
                .map(mapping::toFieldDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
