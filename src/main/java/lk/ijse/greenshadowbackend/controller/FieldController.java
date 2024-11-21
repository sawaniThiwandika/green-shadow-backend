package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin
public class FieldController {
    @Autowired
    FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("fieldSize") String fieldSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2
//            @RequestPart("crops") List<String> crops,
//            @RequestPart("staff") List<String> staff
    ) {
        try {
            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldCode(AppUtil.generateFieldId());
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(fieldLocation);
            fieldDto.setFieldSize(fieldSize);
            fieldDto.setFieldImage1(AppUtil.profilePicToBase64(fieldImage1.getBytes()));
            fieldDto.setFieldImage2(AppUtil.profilePicToBase64(fieldImage2.getBytes()));
//            fieldDto.setCropFieldDetailsDtos(crops);
//            fieldDto.setStaffFieldDetailsDtos(staff);
            fieldService.saveField(fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getFieldList() {

        List<FieldDto> fieldList = fieldService.getFieldList();
        return fieldList;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("fieldSize") String fieldSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2
//            @RequestPart("crops") List<String> crops,
//            @RequestPart("staff") List<String> staff
    ) {
        try {
            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldCode(fieldCode);
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(fieldLocation);
            fieldDto.setFieldSize(fieldSize);
            fieldDto.setFieldImage1(AppUtil.profilePicToBase64(fieldImage1.getBytes()));
            fieldDto.setFieldImage2(AppUtil.profilePicToBase64(fieldImage2.getBytes()));
//          fieldDto.setCropFieldDetailsDtos(crops);
//          fieldDto.setStaffFieldDetailsDtos(staff);
            fieldService.updateField(fieldCode,fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{fieldCode}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteField(@PathVariable("fieldCode") String fieldCode){
        System.out.println("field code"+fieldCode);
        if (fieldCode == null || fieldCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("FieldCode is missing");
        }

        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

}
