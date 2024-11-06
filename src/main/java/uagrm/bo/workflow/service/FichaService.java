package uagrm.bo.workflow.service;

import org.springframework.http.ResponseEntity;
import uagrm.bo.workflow.model.Ficha;

import java.time.LocalDateTime;
import java.util.List;

public interface FichaService {

    Ficha asignarFicha(Long pacienteId, Long especialidadId, Long medicoId,
                       Long horarioId);

    ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                              Long intervaloId, LocalDateTime fecha);

    List<Ficha> listarFichas();

    ResponseEntity<?> agendarFicha(Long pacienteId, Long medicoHorarioId, LocalDateTime fecha);
}
