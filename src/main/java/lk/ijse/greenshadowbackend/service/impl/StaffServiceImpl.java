package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDto dto) {
        System.out.println("Staff Dto: "+dto);
        System.out.println(mapping.toStaffEntity(dto));
        StaffEntity save = staffDao.save(mapping.toStaffEntity(dto));
        if (save == null){

                throw new RuntimeException("Note not saved!!");

        }

    }

    @Override
    public void updateStaff(String staffId, StaffDto dto) {

    }

    @Override
    public void deleteStaff(String id) {

    }

    @Override
    public StaffDto getStaff(String id) {
        return null;
    }

    @Override
    public List<StaffDto> getStaffList() {
        return null;
    }
}
