package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dto.impl.CropLogDetailsDto;
import lk.ijse.greenshadowbackend.service.LogCropDetailsService;

import java.util.ArrayList;
import java.util.List;

public class LogCropDetailsServiceImpl implements LogCropDetailsService {

    @Override
    public List<CropLogDetailsDto> getLogCropDtoList(List<String> cropList, String logId) {
        ArrayList<CropLogDetailsDto> cropLogDetailsDtos = new ArrayList<>();
        for (String cropCode:cropList
             ) {
            cropLogDetailsDtos.add(new CropLogDetailsDto(1,cropCode,logId));
        }
        return cropLogDetailsDtos;

    }
}
