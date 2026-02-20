package com.crm.warehouse.core.controllers;

import com.crm.warehouse.core.dtos.WarehouseRequest;
import com.crm.warehouse.core.dtos.WarehouseResponse;
import com.crm.warehouse.core.services.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<WarehouseResponse> createWarehouse(@RequestBody @Valid WarehouseRequest warehouseRequest) {
       WarehouseResponse warehouseResponse = warehouseService.saveWarehouse(warehouseRequest);
       return new ResponseEntity<>(warehouseResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Все склады")
    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouses() {
        List<WarehouseResponse> warehouseResponseList = warehouseService.findAllWarehouses();
        return new ResponseEntity<>(warehouseResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Склад по id")
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouseById(@PathVariable Integer id) {
        WarehouseResponse warehouseResponse = warehouseService.findWarehouseById(id);
        return new ResponseEntity<>(warehouseResponse, HttpStatus.OK);
    }

    @Operation(summary = "Обновление склада по id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable Integer id, @RequestBody @Valid WarehouseRequest warehouseRequest) {
        warehouseService.updateWarehouseById(id, warehouseRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "'мягкое' удаление склада по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseById(@PathVariable Integer id) {
        warehouseService.deleteWarehouseById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
