package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin(origins = "http://localhost:63342")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveStaff(@RequestBody() StaffDto staffDto) {
        try {
            staffDto.setId(AppUtil.generateStaffId());
            staffDto.setCropStaffDetails(new ArrayList<>());
            staffDto.setStaffFieldDetails(new ArrayList<>());
            staffDto.setStaffLogDetails(new ArrayList<>());
            staffDto.setVehicleDtoList(new ArrayList<>());

            staffService.saveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

}
