package com.irrigation.system.repository;

import com.irrigation.system.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity,Long> {
//    @Query( "SELECT c FROM CONFIGURATION cf WHERE cf.plot_id= :plotId")
    Optional<ConfigurationEntity> findByPlotEntity_Id(Long plotId);
}
