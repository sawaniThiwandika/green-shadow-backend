package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.CropEntity;
import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDao extends JpaRepository<CropEntity,String> {

}
