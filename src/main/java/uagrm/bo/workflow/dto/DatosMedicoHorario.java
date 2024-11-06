package uagrm.bo.workflow.dto;

import lombok.Data;

@Data
public class DatosMedicoHorario {

    Long medicoId;
    Long consultorioId;
    Long horarioId;
    Integer cantDisponibles;
}
