package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dto.impl.LogDto;
import lk.ijse.greenshadowbackend.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Override
    public void saveLog(LogDto dto) {

    }

    @Override
    public void updateLog(String LogId, LogDto dto) {

    }

    @Override
    public void deleteLog(String id) {

    }

    @Override
    public LogDto getLog(String id) {
        return null;
    }

    @Override
    public List<LogDto> getLogList() {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
