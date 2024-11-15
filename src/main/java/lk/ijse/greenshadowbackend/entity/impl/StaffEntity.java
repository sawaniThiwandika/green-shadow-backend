package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="staff")
public class StaffEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String joinedDate;
    private String dob;
    private String contactNo;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String role;
    @OneToMany(mappedBy ="staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffCropDetails> cropStaffDetails;
    @OneToMany(mappedBy ="staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffFieldEntity> staffFieldDetails;
    @OneToMany(mappedBy ="staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<VehicleEntity> vehicleEntityList;
    @OneToMany(mappedBy ="staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<StaffLogDetailsEntity>staffLogDetails ;
}
