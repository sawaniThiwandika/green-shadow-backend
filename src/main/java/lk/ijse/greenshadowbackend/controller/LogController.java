package lk.ijse.greenshadowbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.LogDto;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.service.LogFieldDetailsService;
import lk.ijse.greenshadowbackend.service.LogService;
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
@RequestMapping("api/v1/log")
@CrossOrigin
public class LogController {
    @Autowired
    LogFieldDetailsService logFieldDetailsService;
    @Autowired
    LogService logService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog (
            @RequestPart("logDate") String date,
            @RequestPart("activity") String activity,
            @RequestPart("staff") String staff,
            @RequestPart("crops") String crops,
            @RequestPart("fields") String fields,
            @RequestPart("image") MultipartFile image
    ){
        String logId= AppUtil.generateLogId();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<String> fieldList = objectMapper.readValue(
                    fields,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            ArrayList<String> cropList = objectMapper.readValue(
                    crops,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            List<FieldLogDetailsDto> logFieldDtoList = logFieldDetailsService.getLogFieldDtoList(fieldList,logId);


            LogDto logDto = new LogDto();

            logDto.setLogCode(logId);
            logDto.setLogDate(date);
            logDto.setLogDetails(activity);
            logDto.setObservedImage(AppUtil.profilePicToBase64(image.getBytes()));
            logDto.setFieldLogDetails(logFieldDtoList);



            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
