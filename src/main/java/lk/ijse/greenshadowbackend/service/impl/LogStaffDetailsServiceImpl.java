package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.StaffLogDetailsDto;
import lk.ijse.greenshadowbackend.service.LogStaffDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LogStaffDetailsServiceImpl implements LogStaffDetailsService {
    @Override
    public List<StaffLogDetailsDto> getLogStaffDtoList(List<String> staffList, String logId) {
        ArrayList<StaffLogDetailsDto> staffLogDetailsDtos = new ArrayList<>();
        for (String staffId:staffList
             ) {
            staffLogDetailsDtos.add(new StaffLogDetailsDto(1,staffId,logId));
        }
        return staffLogDetailsDtos;
    }
}
