package com.crm.warehouse.core.services;

import com.crm.warehouse.core.dtos.WarehouseRequest;
import com.crm.warehouse.core.dtos.WarehouseResponse;
import com.crm.warehouse.core.entities.Warehouse;
import com.crm.warehouse.core.exceptions.ResourceNotFoundException;
import com.crm.warehouse.core.mappers.WarehouseMapper;
import com.crm.warehouse.core.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final  WarehouseMapper warehouseMapper;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    public WarehouseResponse saveWarehouse(WarehouseRequest warehouseRequest) {
        Warehouse savedWarehouse = warehouseRepository.save(warehouseMapper.toWarehouse(warehouseRequest));
        //return new ResponseEntity<>(warehouseMapper.toWarehouseResponse(savedWarehouse), HttpStatus.CREATED);
        return warehouseMapper.toWarehouseResponse(savedWarehouse);
    }

    public WarehouseResponse findWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null) return null;
        return warehouseMapper.toWarehouseResponse(warehouse);
    }

    public List<WarehouseResponse> findAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findByActiveIsTrue();
        return warehouses.stream()
                .map(warehouseMapper::toWarehouseResponse).toList();
    }

    public void updateWarehouseById(Integer id, WarehouseRequest warehouseRequest) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null) throw new ResourceNotFoundException("There is no warehouse with ID = " + id);
        warehouse.setName(warehouseRequest.getName());
        warehouse.setLatitude(warehouseRequest.getLatitude());
        warehouse.setLongitude(warehouseRequest.getLongitude());
        warehouse.setCapacity(warehouseRequest.getCapacity());
        warehouseRepository.save(warehouse);
    }

    public void deleteWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null) throw new ResourceNotFoundException("There is no warehouse with ID = " + id);
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
    }
}
