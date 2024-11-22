package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="crop")
public class CropEntity implements SuperEntity {

    @Id
    private String code ;
    private String name;
    private String scientificName;
    private String category;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropFieldDetailsEntity> cropFieldDetails;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffCropDetailsEntity> cropStaffDetails;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropLogDetailsEntity>cropLogDetailsEntities;


}
