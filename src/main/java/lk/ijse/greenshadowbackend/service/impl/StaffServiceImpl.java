package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // Fetch the staff entity to update
        Optional<StaffEntity> optionalStaff = staffDao.findById(staffId);
        if (optionalStaff.isEmpty()) {
            throw new RuntimeException("Staff with ID " + staffId + " not found");
        }

        StaffEntity staffEntity = optionalStaff.get();

        staffEntity.setFirstName(dto.getFirstName());
        staffEntity.setLastName(dto.getLastName());
        staffEntity.setContactNo(dto.getContactNo());
        staffEntity.setEmail(dto.getEmail());
        staffEntity.setAddressLine1(dto.getAddressLine1());
        staffEntity.setAddressLine2(dto.getAddressLine2());
        staffEntity.setAddressLine3(dto.getAddressLine3());
        staffEntity.setAddressLine4(dto.getAddressLine4());
        staffEntity.setAddressLine5(dto.getAddressLine5());
        staffEntity.setJoinedDate(dto.getJoinedDate());
        staffEntity.setDob(dto.getDob());
        staffEntity.setDesignation(dto.getDesignation());
        staffEntity.setRole(dto.getRole());


        if (dto.getStaffFieldDetails() != null) {
            staffEntity.setStaffFieldDetails(mapping.toStaffFieldEntityList(dto.getStaffFieldDetails()));
        }

        if (dto.getCropStaffDetails() != null) {
            staffEntity.setCropStaffDetails(mapping.toStaffCropEntityList(dto.getCropStaffDetails()));
        }

        if (dto.getStaffLogDetails() != null) {
            staffEntity.setStaffLogDetails(mapping.toStaffLogEntityList(dto.getStaffLogDetails()));
        }

        if (dto.getVehicleDtoList() != null) {
            staffEntity.setVehicleEntityList(mapping.toVehicleEntityList(dto.getVehicleDtoList()));
        }

        staffDao.save(staffEntity);
    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> existedUser = staffDao.findById(id);

        if (!existedUser.isPresent()) {
            throw new  RuntimeException("Staff member with id " + id + " not found");
        }

        staffDao.deleteById(id);
    }

    @Override
    public StaffDto getStaff(String id) {
        return null;
    }

    @Override
    public List<StaffDto> getStaffList() {
        List<StaffEntity> allCustomers =staffDao.findAll();
        if(allCustomers.isEmpty()){
            new RuntimeException("Failed to load");
        }
        return allCustomers.stream()
                .map(mapping::toStaffDto)
                .collect(Collectors.toList());

    }

    @Override
    public boolean existsById(String id) {
        return staffDao.existsById(id);
    }

}
