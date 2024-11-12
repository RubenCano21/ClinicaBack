package uagrm.bo.workflow.service;

import org.springframework.http.ResponseEntity;
import uagrm.bo.workflow.dto.FichaDTO;
import uagrm.bo.workflow.model.Ficha;

import java.time.LocalDateTime;
import java.util.List;

public interface FichaService {



    ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                              Long intervaloId, LocalDateTime fecha);

    List<FichaDTO> listarFichas();

//    ResponseEntity<?> agendarFicha(Long pacienteId, Long medicoHorarioId, LocalDateTime fecha);
}
