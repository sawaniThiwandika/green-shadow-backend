package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "staff_log_details")
public class StaffLogDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logCode", nullable = false)
    private LogEntity log;
}
