package uagrm.bo.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialDTO {

    private Long id;
    private String fechaConsulta;
    private String descripcion;
    private Long pacienteId;
    private Long medicoId;
    private Long consultorioId;
    private Long fichaId;


}
