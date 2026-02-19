package com.crm.warehouse.core.services;

import com.crm.warehouse.core.dtos.WarehouseRequest;
import com.crm.warehouse.core.dtos.WarehouseResponse;
import com.crm.warehouse.core.entities.Warehouse;
import com.crm.warehouse.core.mappers.WarehouseMapper;
import com.crm.warehouse.core.repositories.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<WarehouseResponse> saveWarehouse(WarehouseRequest warehouseRequest) {
        Warehouse savedWarehouse = warehouseRepository.save(warehouseMapper.toWarehouse(warehouseRequest));
        return new ResponseEntity<>(warehouseMapper.toWarehouseResponse(savedWarehouse), HttpStatus.CREATED);
    }

    public ResponseEntity<WarehouseResponse> findWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(warehouseMapper.toWarehouseResponse(warehouse), HttpStatus.OK);
    }

    public ResponseEntity<List<WarehouseResponse>> findAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findByActiveIsTrue();
        List<WarehouseResponse> warehouseResponseList = warehouses.stream()
                .map(warehouseMapper::toWarehouseResponse).toList();
        return new ResponseEntity<>(warehouseResponseList, HttpStatus.OK);
    }

    public ResponseEntity<WarehouseResponse> updateWarehouseById(Integer id, WarehouseRequest warehouseRequest) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        saveWarehouse(warehouseRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<WarehouseResponse> deleteWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveIsTrue(id).orElse(null);
        if (warehouse == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
