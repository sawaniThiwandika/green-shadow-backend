package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.StaffEquipmentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentStaffDetailsDao extends JpaRepository<StaffEquipmentDetailsEntity,String> {
}
