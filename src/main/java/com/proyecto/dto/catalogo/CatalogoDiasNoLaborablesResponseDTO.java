package com.proyecto.dto.catalogo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoDiasNoLaborablesResponseDTO {
    private Long id;
    private LocalDate fecha;
    private String descripcion;
}
