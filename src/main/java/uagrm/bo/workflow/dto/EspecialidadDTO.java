package uagrm.bo.workflow.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.MedicoEspecialidad;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadDTO {


    private Long id;
    private String nombre;

    private List<MedicoEspecialidadDTO> medicos;


    public EspecialidadDTO(Especialidad especialidad){
        this.id = especialidad.getId();
        this.nombre = especialidad.getNombre();
        this.medicos = new ArrayList<>();
        for(MedicoEspecialidad medicoEspecialidad : especialidad.getMedicos()){
            this.medicos.add(new MedicoEspecialidadDTO(medicoEspecialidad));
        }
    }
}
