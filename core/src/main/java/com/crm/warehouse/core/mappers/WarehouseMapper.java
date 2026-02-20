package com.crm.warehouse.core.mappers;

import com.crm.warehouse.core.dtos.WarehouseRequest;
import com.crm.warehouse.core.dtos.WarehouseResponse;
import com.crm.warehouse.core.entities.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public WarehouseResponse toWarehouseResponse(Warehouse warehouse) {
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .latitude(warehouse.getLatitude())
                .longitude(warehouse.getLongitude())
                .capacity(warehouse.getCapacity())
                .build();
    }

    public Warehouse toWarehouse(WarehouseRequest warehouseRequest) {
        return Warehouse.builder()
                .name(warehouseRequest.getName())
                .latitude(warehouseRequest.getLatitude())
                .longitude(warehouseRequest.getLongitude())
                .capacity(warehouseRequest.getCapacity())
                .build();
    }
}
