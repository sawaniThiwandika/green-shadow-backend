package lk.ijse.greenshadowbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.FieldLogDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface LogFieldDetailsService {
    public List<FieldLogDetailsDto>getLogFieldDtoList(List<String> fieldList,String logId);
}
