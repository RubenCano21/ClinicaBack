package uagrm.bo.workflow.dto;

import lombok.Getter;
import lombok.Setter;
import uagrm.bo.workflow.model.MedicoEspecialidad;

@Setter
@Getter
public class MedicoEspecialidadDTO {


    private Long id;
    private Long medicoId;
    private Long especialidadId;

    public MedicoEspecialidadDTO() {
    }

    public MedicoEspecialidadDTO(MedicoEspecialidad medicoEspecialidad) {
        this.id = medicoEspecialidad.getId();
        this.medicoId = medicoEspecialidad.getMedico().getId();
        this.especialidadId = medicoEspecialidad.getEspecialidad().getId();
    }

}
