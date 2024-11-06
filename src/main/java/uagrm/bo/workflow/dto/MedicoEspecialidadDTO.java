package uagrm.bo.workflow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicoEspecialidadDTO {


    private Long medicoId;
    private Long especialidadId;

    public MedicoEspecialidadDTO() {
    }

    public MedicoEspecialidadDTO( Long medicoId, Long especialidadId) {

        this.medicoId = medicoId;
        this.especialidadId = especialidadId;
    }

}
