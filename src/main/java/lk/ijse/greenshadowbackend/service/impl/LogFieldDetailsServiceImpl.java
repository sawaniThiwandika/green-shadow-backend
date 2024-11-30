package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import lk.ijse.greenshadowbackend.service.LogFieldDetailsService;

import java.util.ArrayList;
import java.util.List;

public class LogFieldDetailsServiceImpl implements LogFieldDetailsService {

    @Override
    public List<FieldLogDetailsDto> getLogFieldDtoList(List<String> fieldList, String logId) {
        ArrayList<FieldLogDetailsDto> logFieldDetailsDtos = new ArrayList<>();
        for (String fieldId:fieldList
             ) {
            logFieldDetailsDtos.add(new FieldLogDetailsDto(1,fieldId,logId));
        }
        return logFieldDetailsDtos;
    }
}
