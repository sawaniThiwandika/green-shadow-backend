package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDto;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.service.VehicleService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin(origins = "http://localhost:63342")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    StaffService staffService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVehicle(@RequestPart("vehicleCode") String vehicleCode,
                                              @RequestPart("vehicleLicensePlateNumber") String vehicleLicensePlateNumber,
                                              @RequestPart("vehicleCategory") String vehicleCategory,
                                              @RequestPart("vehicleFuelType") String vehicleFuelType,
                                              @RequestPart("vehicleStatus") String vehicleStatus,
                                              @RequestPart("staffId") String staffId,
                                              @RequestPart("vehicleRemarks") String vehicleRemarks) {


        try {
            StaffDto staff = staffService.getStaff(staffId);
            String generateVehicleCode = AppUtil.generateVehicleCode();

            vehicleService.saveVehicle(new VehicleDto(generateVehicleCode,vehicleLicensePlateNumber,vehicleCategory,vehicleFuelType,vehicleStatus,vehicleRemarks,staff));
            return new ResponseEntity<>("Staff created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDto> getVehicleList() {
        List<VehicleDto> vehicleList = vehicleService.getVehicleList();
        System.out.println("Vehicle list: "+vehicleList);
        return vehicleList;


    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateVehicle(@RequestPart("vehicleCode") String vehicleCode,
                              @RequestPart("vehicleLicensePlateNumber") String vehicleLicensePlateNumber,
                              @RequestPart("vehicleCategory") String vehicleCategory,
                              @RequestPart("vehicleFuelType") String vehicleFuelType,
                              @RequestPart("vehicleStatus") String vehicleStatus,
                              @RequestPart("staffId") String staffId,
                              @RequestPart("vehicleRemarks") String vehicleRemarks) {
        StaffDto staff = staffService.getStaff(staffId);
        String generateVehicleCode = AppUtil.generateVehicleCode();
        vehicleService.updateVehicle(vehicleCode,new VehicleDto(generateVehicleCode,vehicleLicensePlateNumber,vehicleCategory,vehicleFuelType,vehicleStatus,vehicleRemarks,staff));


    }

    @DeleteMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        if (vehicleCode == null || vehicleCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("vehicle ID is missing");
        }

        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

}
