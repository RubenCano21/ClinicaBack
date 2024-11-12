package uagrm.bo.workflow.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uagrm.bo.workflow.model.Horario;

import java.time.LocalTime;

@Data

public class HorarioDTO {

    private Long id;

    @NotNull
    private String dia;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;

    public HorarioDTO(Horario horario) {
        this.id = horario.getId();
        this.dia = horario.getDia();
        this.horaInicio = horario.getHoraInicio();
        this.horaFin = horario.getHoraFin();
    }
}
