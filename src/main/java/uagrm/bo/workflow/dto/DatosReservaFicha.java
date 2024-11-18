package uagrm.bo.workflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.Ficha;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosReservaFicha{

        @NotNull(message = "El ID del paciente no puede ser nulo")
        private Long pacienteId;

        @NotNull(message = "La especialidad no puede ser nula")
        private Long especialidadId;

        @NotNull(message = "El ID del médico no puede ser nulo")
        private Long medicoId;

        @NotNull(message = "El ID del horario no puede ser nulo")
        private Long medicoHorarioId;

        @NotNull(message = "El ID del intervalo no puede ser nulo")
        private Long intervaloHorarioId;

        @NotNull(message = "La fecha de consulta no puede ser nula")
        private LocalDateTime fechaConsulta;

    public DatosReservaFicha(Ficha ficha){
            this.pacienteId = ficha.getPaciente().getId();
            this.medicoId = ficha.getMedico().getId();
            this.especialidadId = ficha.getEspecialidad().getId();
            this.medicoHorarioId = ficha.getMedicoHorario().getId();
            this.intervaloHorarioId = ficha.getIntervaloHorario().getId();
            this.fechaConsulta = ficha.getFechaConsulta();
        }
}
