package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.FieldEntity;
import lk.ijse.greenshadowbackend.entity.impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<LogEntity,String> {
}
