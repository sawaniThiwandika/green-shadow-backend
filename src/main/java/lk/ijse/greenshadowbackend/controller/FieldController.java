package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.service.VehicleService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin(origins = "http://localhost:63342")
public class FieldController {
    @Autowired
    FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdateStaff(@RequestBody FieldDto fieldDto) {
        try {
            fieldDto.setFieldCode(AppUtil.generateFieldId());
            fieldDto.setFieldEquipmentDetailsDtos(new ArrayList<>());
            fieldDto.setFieldLogDetailsDtos(new ArrayList<>());
            fieldDto.setCropFieldDetailsDtos(new ArrayList<>());
            String base64ProPic1 = "";
            String base64ProPic2 = "";
            try {
                byte[] bytesProPic1 = fieldDto.getFieldImage1().getBytes();
                byte[] bytesProPic2 = fieldDto.getFieldImage2().getBytes();
                base64ProPic1 = AppUtil.profilePicToBase64(bytesProPic1);
                base64ProPic2 = AppUtil.profilePicToBase64(bytesProPic2);

                fieldDto.setFieldImage1(base64ProPic1);
                fieldDto.setFieldImage2(base64ProPic2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            fieldService.saveField(fieldDto);
            return new ResponseEntity<>("Field created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
