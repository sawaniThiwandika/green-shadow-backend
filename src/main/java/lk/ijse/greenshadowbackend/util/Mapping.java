package lk.ijse.greenshadowbackend.util;

import lk.ijse.greenshadowbackend.dao.CropFieldDetailsDao;
import lk.ijse.greenshadowbackend.dto.impl.*;
import lk.ijse.greenshadowbackend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    //for user mapping
    public UserEntity toUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserDto toUserDtO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
    public VehicleEntity toVehicleEntity(VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto, VehicleEntity.class);
    }
    public VehicleDto toVehicleDto(VehicleEntity vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }
    public StaffEntity toStaffEntity(StaffDto staffDto){
        return modelMapper.map(staffDto, StaffEntity.class);
    }
    public StaffDto toStaffDto(StaffEntity staffEntity){
        return modelMapper.map(staffEntity, StaffDto.class);
    }
    public FieldEntity toFieldEntity(FieldDto fieldDto){
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldDto toFieldDto(FieldEntity fieldEntity){
        return modelMapper.map(fieldEntity, FieldDto.class);
    }
    public CropEntity toCropEntity(CropDto cropDto){
        return modelMapper.map(cropDto, CropEntity.class);
    }
    public CropDto toCropDto(CropEntity cropEntity){
        return modelMapper.map(cropEntity, CropDto.class);
    }
    public CropFieldDetailsDto toCropFieldDetailDto(CropFieldDetailsEntity cropFieldEntity){
        return modelMapper.map(cropFieldEntity, CropFieldDetailsDto.class);
    }
    public CropFieldDetailsEntity toCropFieldDetailEntity(CropFieldDetailsDto cropFieldDto){
        return modelMapper.map(cropFieldDto, CropFieldDetailsEntity.class);
    }
    public EquipmentEntity toEquipmentEntity(EquipmentDto equipmentDto){
        return modelMapper.map(equipmentDto, EquipmentEntity.class);
    }
    public EquipmentDto toEquipmentDto(EquipmentEntity equipmentEntity){
        return modelMapper.map(equipmentEntity, EquipmentDto.class);
    }
    public LogEntity toLogEntity(LogDto logDto){
        return modelMapper.map(logDto, LogEntity.class);
    }
    public LogDto toLogDto(LogEntity logEntity){
        return modelMapper.map(logEntity, LogDto.class);
    }
    public List<StaffDto> toStaffDtoList(List<StaffEntity> list){
        System.out.println(list.size());
        return modelMapper.map(list,new TypeToken<List<StaffDto>>(){}.getType());
    }
    public List<StaffEntity> toStaffEntityList(List<StaffDto> list){
        System.out.println(list.size());
        return modelMapper.map(list,new TypeToken<List<StaffEntity>>(){}.getType());
    }
    public List<StaffFieldEntityDetailsEntity> toStaffFieldEntityList(List<StaffFieldDetailsDto> list){
        return modelMapper.map(list,new TypeToken<List<StaffFieldEntityDetailsEntity>>(){}.getType());
    }
    public List<StaffCropDetailsEntity> toStaffCropEntityList(List<StaffCropDetailsDto> list){
        return modelMapper.map(list,new TypeToken<List<StaffCropDetailsEntity>>(){}.getType());
    }
    public List<StaffLogDetailsEntity> toStaffLogEntityList(List<StaffLogDetailsDto> list){
        return modelMapper.map(list,new TypeToken<List<StaffLogDetailsEntity>>(){}.getType());
    }

    public List<CropDto> toCropDtoList(List<CropEntity> list){
        return modelMapper.map(list,new TypeToken<List<CropDto>>(){}.getType());
    }
    public List<FieldDto> toFieldDtoList(List<FieldEntity> list){
        return modelMapper.map(list,new TypeToken<List<FieldDto>>(){}.getType());
    }
    public List<EquipmentDto> toEquipmentDtoList(List<EquipmentEntity> list){
        return modelMapper.map(list,new TypeToken<List<EquipmentDto>>(){}.getType());
    }
    public List<LogDto> toLogDtoList(List<LogEntity> list){
        return modelMapper.map(list,new TypeToken<List<LogDto>>(){}.getType());
    }
    public List<VehicleDto> toVehicleDtoList(List<VehicleEntity> list){
        return modelMapper.map(list,new TypeToken<List<VehicleDto>>(){}.getType());
    }
    public List<VehicleEntity> toVehicleEntityList(List<VehicleDto> list){
        return modelMapper.map(list,new TypeToken<List<VehicleEntity>>(){}.getType());
    }

}
