package com.javanauta.bffagendadortarefas.business.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {

    private Long id;
    private String numero;
    private String ddd;

}
