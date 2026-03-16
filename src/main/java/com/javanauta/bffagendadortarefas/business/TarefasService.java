package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusTarefa;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravaTarefas(String token, TarefasDTORequest dto) {
        return tarefasClient.gravaTarefa(dto, token);
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                     LocalDateTime dataFinal,
                                                                     String token) {
        return tarefasClient.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletarTarefasPorId(String id, String token) {
        tarefasClient.deletarTarefasPorId(id, token);
    }

    public TarefasDTOResponse alterarStatusNotificacao(StatusTarefa statusTarefa, String id, String token) {
        return tarefasClient.alterarStatusNotificacao(statusTarefa, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, token);
    }
}
