package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "crop_log_details")
public class CropLogDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cropCode", nullable = false)
    private CropEntity crop;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logCode", nullable = false)
    private LogEntity log;

}
