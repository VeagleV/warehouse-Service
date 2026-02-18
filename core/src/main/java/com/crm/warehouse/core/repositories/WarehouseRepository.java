package com.crm.warehouse.core.repositories;

import com.crm.warehouse.core.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WarehouseRepository  extends JpaRepository<Warehouse, Integer> {

    boolean existsByIdAndActiveIsTrue(Integer id);

}
