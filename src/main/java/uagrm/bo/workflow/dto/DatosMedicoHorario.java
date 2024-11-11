package uagrm.bo.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import uagrm.bo.workflow.model.MedicoHorario;

@Data
//@JsonIgnoreProperties({"medicoId", "consultorioId", "horarioId"})
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
