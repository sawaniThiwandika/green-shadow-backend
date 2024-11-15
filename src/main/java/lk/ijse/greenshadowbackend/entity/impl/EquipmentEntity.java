package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="equipment")
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String equipmentName ;
    private String equipmentType;
    private String equipmentStatus;
    @OneToMany(mappedBy ="equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<FieldEquipmentDetails> fieldEquipmentDetails;
    @OneToMany(mappedBy ="equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffEquipmentDetails> staffEquipmentDetails;

}
