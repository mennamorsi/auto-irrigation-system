package com.irrigation.system.repository;

import com.irrigation.system.entity.PlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlotRepository extends JpaRepository<PlotEntity,Long> {
    Optional<PlotEntity> findByplotCode(String plotCode);

}
