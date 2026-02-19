package com.crm.warehouse.core.controllers;

import com.crm.warehouse.core.dtos.WarehouseRequest;
import com.crm.warehouse.core.dtos.WarehouseResponse;
import com.crm.warehouse.core.services.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@Tag(name = "Работа со складами")
@Validated
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Добавление нового склада")
    @PostMapping
    public ResponseEntity<WarehouseResponse> createWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        return warehouseService.saveWarehouse(warehouseRequest);
    }

    @Operation(summary = "Все склады")
    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouses() {
        return warehouseService.findAllWarehouses();
    }

    @Operation(summary = "Склад по id")
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouseById(@PathVariable Integer id) {
        return warehouseService.findWarehouseById(id);
    }

    @Operation(summary = "обновление склада по id")
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(@PathVariable Integer id, @RequestBody WarehouseRequest warehouseRequest) {
        return warehouseService.updateWarehouseById(id, warehouseRequest);
    }

    @Operation(summary = "'мягкое' удаление склада по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<WarehouseResponse> deleteWarehouseById(@PathVariable Integer id) {
        return warehouseService.deleteWarehouseById(id);
    }




}
