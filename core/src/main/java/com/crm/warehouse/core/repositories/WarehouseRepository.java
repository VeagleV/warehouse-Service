package com.crm.warehouse.core.repositories;

import com.crm.warehouse.core.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WarehouseRepository  extends JpaRepository<Warehouse, Integer> {
    List<Warehouse> findByActiveIsTrue();
    Optional<Warehouse> findByIdAndActiveIsTrue(Integer id);
}
