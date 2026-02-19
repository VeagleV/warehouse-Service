package com.crm.warehouse.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DTO склада(Request)")
public class WarehouseRequest {

    @JsonProperty
    @NotBlank
    private String name;

    @JsonProperty
    @DecimalMax(value = "90.0", message = "Широта не может быть больше 90")
    @DecimalMin(value = "-90.0", message = "Широта не может быть меньше -90")
    @NotNull
    private Double latitude;

    @JsonProperty
    @DecimalMax(value = "180.0", message = "Долгота не может быть больше 180")
    @DecimalMin(value = "-180.0", message = "Долгота не может быть меньше -180")
    @NotNull
    private Double longitude;

    @PositiveOrZero
    @JsonProperty
    @NotNull
    private Integer capacity;
}
