package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    StaffService staffService;
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdateStaff(@RequestBody StaffDto staffDto) {
        try {
                staffDto.setId(AppUtil.generateStaffId());
                staffDto.setCropStaffDetails(new ArrayList<>());
                staffDto.setStaffFieldDetails(new ArrayList<>());
                staffDto.setStaffLogDetails(new ArrayList<>());
                staffDto.setVehicleDtoList(new ArrayList<>());

                staffService.saveStaff(staffDto);
                return new ResponseEntity<>("Staff created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getItemList() {
        List<StaffDto> staffList = staffService.getStaffList();
        return staffList;


    }
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStaff(@RequestBody() StaffDto staffDto){
        System.out.println(staffDto.getId());
        staffService.updateStaff(staffDto.getId(),staffDto);

    }
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @DeleteMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCustomer(@PathVariable("staffId") String staffId){
        if (staffId == null || staffId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer ID is missing");
        }

        try {
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


}
