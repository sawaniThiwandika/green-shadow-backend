package lk.ijse.greenshadowbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.greenshadowbackend.dto.impl.CropDto;
import lk.ijse.greenshadowbackend.dto.impl.CropFieldDetailsDto;
import lk.ijse.greenshadowbackend.service.CropService;
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
@RequestMapping("api/v1/crop")
@CrossOrigin

public class CropController {

    @Autowired
    CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("fieldDetails") String fieldDetails,
            @RequestPart("file") MultipartFile file

    ) {
        String cropCode = AppUtil.generateCropCode();

        try {
            System.out.println("common Name: " + commonName);

                ObjectMapper objectMapper = new ObjectMapper();
                ArrayList<String> fields = objectMapper.readValue(
                        fieldDetails,
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
                );
                List<CropFieldDetailsDto> cropFieldList = cropService.getCropFieldList(cropCode, fields);

                System.out.println("Fields: " + fields.get(0));
                System.out.println("cropFieldsList : " + cropFieldList.get(0));


            CropDto cropDto = new CropDto();

            cropDto.setCode(cropCode);
            cropDto.setName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setImage(AppUtil.profilePicToBase64(file.getBytes()));
            cropDto.setCropFieldDetails(cropFieldList);
            cropService.saveCrop(cropDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getCropList(){
        List<CropDto> cropList = cropService.getCropList();

        for (int i=0;i<cropList.size();i++){
            System.out.println("Field of crop : "+cropList.get(i).getCropFieldDetails());
        }

       // System.out.println("crop list -: "+cropList);
        return cropList;

    }

    @PutMapping(value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateCrop(@PathVariable("cropCode") String cropCode,
                                             @RequestPart("commonName") String commonName,
                                             @RequestPart("scientificName") String scientificName,
                                             @RequestPart("category") String category,
                                             @RequestPart("season") String season,
                                             @RequestPart("fieldDetails") String fieldDetails,
                                             @RequestPart("file") MultipartFile file){


        try {
            System.out.println("common Name: " + commonName);

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<String> fields = objectMapper.readValue(
                    fieldDetails,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class)
            );
            List<CropFieldDetailsDto> cropFieldList = cropService.getCropFieldList(cropCode, fields);

            System.out.println("Fields: " + fields.get(0));
            System.out.println("cropFieldsList : " + cropFieldList.get(0));

            CropDto cropDto = new CropDto();

            cropDto.setCode(cropCode);
            cropDto.setName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setImage(AppUtil.profilePicToBase64(file.getBytes()));
            cropDto.setCropFieldDetails(cropFieldList);
            cropService.updateCrop(cropCode,cropDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
