package uagrm.bo.workflow.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.model.Historial;
import uagrm.bo.workflow.model.Paciente;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {


    private Long id;
    @NotNull
    private Integer ci;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private Date fechaNacimiento;

//    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;
    private String sexo;
    private String direccion;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.ci = paciente.getCi();
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        this.email = paciente.getEmail();
        this.telefono = paciente.getTelefono();
        this.sexo = paciente.getSexo();
        this.direccion = paciente.getDireccion();
    }

}
