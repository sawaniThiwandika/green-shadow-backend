package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "staff_equipment_details")
public class StaffEquipmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentId", nullable = false)
    private EquipmentEntity equipment;
}
