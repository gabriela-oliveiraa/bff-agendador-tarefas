package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusTarefa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravaTarefa(@RequestBody TarefasDTORequest dto,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(@RequestParam
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataIncial,
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                              @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusNotificacao(@RequestParam("status") StatusTarefa status,
                                                @RequestParam("id") String id,
                                                @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefasPorId(@RequestParam("id") String id,
                             @RequestHeader("Authorization") String token);
}
