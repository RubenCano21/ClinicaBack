package uagrm.bo.workflow.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.Medico;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTO {


    private Long id;
    @NotNull
    private Integer ci;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String sexo;


    public MedicoDTO(Medico medico) {
        this.id = medico.getId();
        this.ci = medico.getCi();
        this.nombre = medico.getNombre();
        this.apellido = medico.getApellido();
        this.email = medico.getEmail();
        this.telefono = medico.getTelefono();
        this.sexo = medico.getSexo();
    }


}
