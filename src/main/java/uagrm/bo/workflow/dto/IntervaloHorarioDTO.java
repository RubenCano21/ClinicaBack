package uagrm.bo.workflow.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import uagrm.bo.workflow.model.IntervalosHorario;

@Data
//@JsonIgnoreProperties({"medicoHorarioId", "fichaId"})
public class IntervaloHorarioDTO {

    private Long id;
    private Long medicoHorarioId;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private Long fichaId;

    public IntervaloHorarioDTO() {
    }

    public IntervaloHorarioDTO(IntervalosHorario intervalosHorario) {
        this.id = intervalosHorario.getId();
        this.medicoHorarioId = intervalosHorario.getMedicoHorario().getId();
        this.horaInicio = intervalosHorario.getHoraInicio().toString();
        this.horaFin = intervalosHorario.getHoraFin().toString();
        this.estado = intervalosHorario.getEstado().name();
        this.fichaId = intervalosHorario.getFicha() != null ? intervalosHorario.getFicha().getId() : null;
    }
}
