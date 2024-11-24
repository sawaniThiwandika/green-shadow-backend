package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="crop_field_details")
public class CropFieldDetailsEntity implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cropCode", nullable = false)
    private CropEntity crop;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity field;
}
