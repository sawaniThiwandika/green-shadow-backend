package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dao.VehicleDao;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDto;
import lk.ijse.greenshadowbackend.entity.impl.StaffEntity;
import lk.ijse.greenshadowbackend.entity.impl.VehicleEntity;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.service.VehicleService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private StaffService staffService;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDto dto) {
        System.out.println("Vehicle Dto: "+dto);
        System.out.println(mapping.toVehicleEntity(dto));
        System.out.println("Before save vehicle :"+ mapping.toVehicleEntity(dto));
        VehicleEntity save = vehicleDao.save( mapping.toVehicleEntity(dto));
        if (save == null){

            throw new RuntimeException("Note not saved!!");

        }
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDto dto) {
        // Fetch the staff entity to update
        Optional<VehicleEntity> optionalVehicle = vehicleDao.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new RuntimeException("Staff with ID " + vehicleId + " not found");
        }

        VehicleEntity vehicleEntity = optionalVehicle.get();

        vehicleEntity.setVehicleCategory(dto.getVehicleCategory());
        vehicleEntity.setStaff(mapping.toStaffEntity(dto.getStaffId()));
        vehicleEntity.setVehicleRemarks(dto.getVehicleRemarks());
        vehicleEntity.setVehicleStatus(dto.getVehicleStatus());
        vehicleEntity.setVehicleFuelType(dto.getVehicleFuelType());
        vehicleEntity.setVehicleLicensePlateNumber(dto.getVehicleLicensePlateNumber());


        vehicleDao.save(vehicleEntity);
    }

    @Override
    public void deleteVehicle(String id) {

    }

    @Override
    public VehicleDto getVehicle(String id) {

        return null;
    }

    @Override
    public List<VehicleDto> getVehicleList() {
        List<VehicleEntity> allCustomers =vehicleDao.findAll();
        if(allCustomers.isEmpty()){
            new RuntimeException("Failed to load");
        }
        return allCustomers.stream()
                .map(mapping::toVehicleDto)
                .collect(Collectors.toList());

    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
