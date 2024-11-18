package uagrm.bo.workflow.dto;

import lombok.Getter;
import lombok.Setter;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;

@Setter
@Getter
public class MedicoEspecialidadDTO {


    private Long id;
    private Medico medico;
    private Especialidad especialidad;


    public MedicoEspecialidadDTO(MedicoEspecialidad medicoEspecialidad) {
        this.id = medicoEspecialidad.getId();
        this.medico = medicoEspecialidad.getMedico();
        this.especialidad = medicoEspecialidad.getEspecialidad();
    }

}
