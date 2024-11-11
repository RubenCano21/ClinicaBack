package uagrm.bo.workflow.dto;

import lombok.Data;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoHorario;

@Data
//@JsonIgnoreProperties({"medicoId", "consultorioId", "horarioId"})
public class MedicoHorarioDTO {

    Medico medico;
    Consultorio consultorio;
    Horario horario;
    Integer cantDisponibles;

    public MedicoHorarioDTO(MedicoHorario medicoHorario) {
        this.medico = medicoHorario.getMedico();
        this.consultorio = medicoHorario.getConsultorio();
        this.horario = medicoHorario.getHorario();
        this.cantDisponibles = medicoHorario.getCantDisponibles();
    }
}
