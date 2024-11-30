package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowbackend.dto.impl.LogDto;

import java.util.List;

public interface LogService {
    public void saveLog(LogDto dto);
    public void updateLog(String LogId, LogDto dto);
    public void deleteLog(String id);
    public LogDto getLog(String id);
    public List<LogDto> getLogList();

    boolean existsById(String id);
}
