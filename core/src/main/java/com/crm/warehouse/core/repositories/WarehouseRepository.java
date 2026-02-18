package com.crm.warehouse.core.repositories;

import com.crm.warehouse.core.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository  extends JpaRepository<Warehouse, Integer> {

    Optional<Warehouse> findById(Integer id);

    boolean existsById(Integer id);

    boolean existsByIdAndActiveIsTrue(Integer id);

}
