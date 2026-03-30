package com.javanauta.bffagendadortarefas.business.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTORequest> telefones;

}
