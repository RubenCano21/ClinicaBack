package uagrm.bo.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.MedicoHorario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosMedicoHorario {

    Long medicoId;
    Long consultorioId;
    Long horarioId;
    Integer cantDisponibles;

    public DatosMedicoHorario(MedicoHorario medicoHorario) {
        this.medicoId = medicoHorario.getMedico().getId();
        this.consultorioId = medicoHorario.getConsultorio().getId();
        this.horarioId = medicoHorario.getHorario().getId();
        this.cantDisponibles = medicoHorario.getCantDisponibles();
    }
}
