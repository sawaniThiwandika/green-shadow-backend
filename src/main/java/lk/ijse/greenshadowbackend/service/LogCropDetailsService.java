package lk.ijse.greenshadowbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.CropLogDetailsDto;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public interface LogCropDetailsService {
    public List<CropLogDetailsDto> getLogCropDtoList(List<String> cropList, String logId);
}
