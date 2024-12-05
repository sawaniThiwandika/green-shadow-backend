package lk.ijse.greenshadowbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.greenshadowbackend.dto.impl.*;
import lk.ijse.greenshadowbackend.service.*;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    LogCropDetailsService logCropDetailsService;
    @Autowired
    LogStaffDetailsService logStaffDetailsService;
    @Autowired
    LogService logService;
    @PreAuthorize("hasAnyRole('MANAGER','SCIENTIST')")
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
            ArrayList<String> staffList = objectMapper.readValue(
                   staff,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            List<FieldLogDetailsDto> logFieldDtoList = logFieldDetailsService.getLogFieldDtoList(fieldList,logId);
            List<CropLogDetailsDto> logCropDDtoList= logCropDetailsService.getLogCropDtoList(cropList,logId);
            List<StaffLogDetailsDto> logStaffDDtoList= logStaffDetailsService.getLogStaffDtoList(staffList,logId);


            LogDto logDto = new LogDto();

            logDto.setLogCode(logId);
            logDto.setLogDate(date);
            logDto.setLogDetails(activity);
            logDto.setObservedImage(AppUtil.profilePicToBase64(image.getBytes()));
            logDto.setFieldLogDetails(logFieldDtoList);
            logDto.setCropLogDetails(logCropDDtoList);
            logDto.setStaffLogDetails(logStaffDDtoList);
            //System.out.println("Log dto in Log Controller "+ logDto);
            logService.saveLog(logDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAnyRole('MANAGER','SCIENTIST')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDto> getLogList(){
        List<LogDto> logList = logService.getLogList();
        // System.out.println("crop list -: "+cropList);
        return logList;

    }
    @PreAuthorize("hasAnyRole('MANAGER','SCIENTIST')")
    @PutMapping(value = "/{logCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateLog(
            @RequestPart("logDate") String date,
            @RequestPart("activity") String activity,
            @RequestPart("staff") String staff,
            @RequestPart("crops") String crops,
            @RequestPart("fields") String fields,
            @RequestPart("image") MultipartFile image,
            @PathVariable("logCode") String logId
    ){


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
            ArrayList<String> staffList = objectMapper.readValue(
                    staff,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            List<FieldLogDetailsDto> logFieldDtoList = logFieldDetailsService.getLogFieldDtoList(fieldList,logId);
            List<CropLogDetailsDto> logCropDDtoList= logCropDetailsService.getLogCropDtoList(cropList,logId);
            List<StaffLogDetailsDto> logStaffDDtoList= logStaffDetailsService.getLogStaffDtoList(staffList,logId);


            LogDto logDto = new LogDto();

            logDto.setLogCode(logId);
            logDto.setLogDate(date);
            logDto.setLogDetails(activity);
            logDto.setObservedImage(AppUtil.profilePicToBase64(image.getBytes()));
            logDto.setFieldLogDetails(logFieldDtoList);
            logDto.setCropLogDetails(logCropDDtoList);
            logDto.setStaffLogDetails(logStaffDDtoList);
            //System.out.println("Log dto in Log Controller "+ logDto);
            logService.updateLog(logId,logDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('MANAGER','SCIENTIST')")
    @DeleteMapping(value = "/{logCode}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteLog(@PathVariable("logCode") String logCode){
        System.out.println("Log code"+logCode);
        if (logCode == null || logCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("LogCode is missing");
        }

        try {
            logService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }




}
