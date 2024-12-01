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
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @OneToMany(mappedBy ="log", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffLogDetailsEntity> staffLogDetailsEntities;
    @OneToMany(mappedBy ="log", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropLogDetailsEntity> cropLogDetailsEntities;
    @OneToMany(mappedBy ="log", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<FieldLogDetailsEntity>fieldLogDetailsEntities;
}
