package com.crm.warehouse.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DTO склада(Response)")
public class WarehouseResponse {

    @JsonProperty
    @NotBlank
    private Integer id;

    @JsonProperty
    @NotBlank
    private String name;

    @JsonProperty
    @DecimalMax(value = "90.0", message = "Широта не может быть больше 90")
    @DecimalMin(value = "-90.0", message = "Широта не может быть меньше -90")
    private Double latitude;

    @JsonProperty
    @DecimalMax(value = "180.0", message = "Долгота не может быть больше 180")
    @DecimalMin(value = "-180.0", message = "Долгота не может быть меньше -180")
    private Double longitude;

    @JsonProperty
    @PositiveOrZero
    private Integer capacity;

}
