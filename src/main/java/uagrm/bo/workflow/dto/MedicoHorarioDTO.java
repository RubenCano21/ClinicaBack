package uagrm.bo.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoHorario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoHorarioDTO {

    Long id;
    Medico medico;
    Consultorio consultorio;
    Horario horario;
    Integer cantDisponibles;

    public MedicoHorarioDTO(MedicoHorario medicoHorario) {
        this.id = medicoHorario.getId();
        this.medico = medicoHorario.getMedico();
        this.consultorio = medicoHorario.getConsultorio();
        this.horario = medicoHorario.getHorario();
        this.cantDisponibles = medicoHorario.getCantDisponibles();
    }
}
