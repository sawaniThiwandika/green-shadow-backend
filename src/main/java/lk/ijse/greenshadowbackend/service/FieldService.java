package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;

import java.util.List;

public interface FieldService {
    public void saveField(FieldDto dto);
    public void updateField(String FieldId, FieldDto dto);
    public void deleteField(String id);
    public FieldDto getField(String id);
    public List<FieldDto> getFieldList();

    boolean existsById(String id);
}
