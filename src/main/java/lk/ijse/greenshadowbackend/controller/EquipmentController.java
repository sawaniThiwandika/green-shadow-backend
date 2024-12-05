package lk.ijse.greenshadowbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.greenshadowbackend.dto.impl.*;
import lk.ijse.greenshadowbackend.service.*;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin

public class EquipmentController {
    @Autowired
    EquipmentFieldDetailsService equipmentFieldDetailsService;
    @Autowired
    EquipmentStaffDetailsService equipmentStaffDetailsService;

    @Autowired
    EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment (
            @RequestPart("equipmentId") String id,
            @RequestPart("equipmentName") String name,
            @RequestPart("equipmentType") String type,
            @RequestPart("equipmentStatus") String status,
            @RequestPart("equipmentAssignedStaff") String staff,
            @RequestPart("equipmentAssignedField") String fields
    ){
        String equipmentId= AppUtil.generateEquipmentId();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<String> fieldList = objectMapper.readValue(
                    fields,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            ArrayList<String> staffList = objectMapper.readValue(
                    staff,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );

            List<FieldEquipmentDetailsDto> fieldEquipmentDetailsDtos= equipmentFieldDetailsService.getEquipmentFieldDtoList(fieldList,equipmentId);
            List<StaffEquipmentDetailsDto> staffEquipmentDetailsDtos= equipmentStaffDetailsService.getEquipmentStaffDtoList(staffList,equipmentId);


            EquipmentDto equipmentDto = new EquipmentDto();

            equipmentDto.setEquipmentId(equipmentId);
            equipmentDto.setEquipmentName(name);
            equipmentDto.setEquipmentStatus(status);
            equipmentDto.setEquipmentType(type);
            equipmentDto.setStaffEquipmentDetails(staffEquipmentDetailsDtos);
            equipmentDto.setFieldEquipmentDetails(fieldEquipmentDetailsDtos);
            System.out.println("Equipment dto in  Controller "+ equipmentDto);
            equipmentService.saveEquipment(equipmentDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
