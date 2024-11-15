package lk.ijse.greenshadowbackend.entity.impl;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="crop")
public class CropEntity implements SuperEntity {

    @Id
    private String code ;
    private String name;
    private String scientificName;
    private String category;
    private String season;
    private String image;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropFieldDetails> cropFieldDetails;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffCropDetails> cropStaffDetails;
    @OneToMany(mappedBy ="crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<CropLogDetailsEntity>cropLogDetailsEntities;


}
