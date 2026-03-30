package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusTarefa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefaProximaHora() {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);
        log.info("Tarefas Encontradas " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("Email enviado para usuario " + tarefa.getEmailUsuario());
            tarefasService.alterarStatusNotificacao(StatusTarefa.NOTIFICADO, tarefa.getId(), token);
        });

        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTORequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
