package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="vehicle")

public class VehicleEntity {
    @Id
    private String vehicleCode;
    private String vehicleLicensePlateNumber;
    private String vehicleCategory;
    private String vehicleFuelType;
    private String vehicleStatus;
    private String vehicleRemarks;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
}
