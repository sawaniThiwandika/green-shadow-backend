package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    @OneToMany(mappedBy ="field", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropFieldDetailsEntity> cropFieldDetails;
    @OneToMany(mappedBy ="field", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffFieldEntityDetailsEntity> staffFieldDetails;
    @OneToMany(mappedBy ="field", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<FieldEquipmentDetailsEntity> fieldEquipmentDetails;
    @OneToMany(mappedBy ="field", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<FieldLogDetailsEntity> fieldLogDetailsEntities;

}
