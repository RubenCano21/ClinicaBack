package uagrm.bo.workflow.service;

import uagrm.bo.workflow.dto.FichaDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface FichaService {



    void asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                 Long intervaloId, LocalDateTime fecha);

    List<FichaDTO> listarFichas();

//    ResponseEntity<?> agendarFicha(Long pacienteId, Long medicoHorarioId, LocalDateTime fecha);
}
