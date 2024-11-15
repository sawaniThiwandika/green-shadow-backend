package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "field_log_details")
public class FieldLogDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logId", nullable = false)
    private LogEntity log;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldtId", nullable = false)
    private FieldEntity field;
}
