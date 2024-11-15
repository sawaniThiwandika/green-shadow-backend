package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "field_equipment_details")
public class FieldEquipmentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldId", nullable = false)
    private FieldEntity field;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentId", nullable = false)
    private EquipmentEntity equipment;
}
