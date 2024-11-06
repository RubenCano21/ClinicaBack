package uagrm.bo.workflow.dto;

import uagrm.bo.workflow.model.Especialidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosReservaFicha(

        Long pacienteId,
        Long medicoId,
        Especialidad especialidad,
        LocalDateTime fecha
) {
}
