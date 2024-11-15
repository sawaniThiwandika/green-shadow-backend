package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    private String relevantFields;
    @OneToMany(mappedBy ="log", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffLogDetailsEntity> staffLogDetails;
    @OneToMany(mappedBy ="log", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropLogDetailsEntity> cropLogDetailsEntities;
}