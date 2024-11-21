package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void updateField(String FieldId, FieldDto dto) {

    }

    @Override
    public void deleteField(String id) {

    }

    @Override
    public FieldDto getField(String id) {
        return null;
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
