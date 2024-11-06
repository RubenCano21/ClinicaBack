package uagrm.bo.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FichaDTO {

    private Long id;
    private Long pacienteId;
    private Long especialidadId;
    private Long medicoId;
    private Long medicoHorarioId;
    private Long horarioId;
    private Long intervaloId;
    private LocalDateTime fecha;
    private Integer cantDisponibles;

}
