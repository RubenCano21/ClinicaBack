package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.dto.PacienteDTO;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true)
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

    @OneToMany(mappedBy = "paciente")
    private List<Historial> historiales;

    public Paciente(@org.jetbrains.annotations.NotNull PacienteDTO pacienteDTO){
        this.id = pacienteDTO.getId();
        this.ci = pacienteDTO.getCi();
        this.nombre = pacienteDTO.getNombre();
        this.apellido = pacienteDTO.getApellido();
        this.fechaNacimiento = pacienteDTO.getFechaNacimiento();
        this.email = pacienteDTO.getEmail();
        this.telefono = pacienteDTO.getTelefono();
        this.sexo = pacienteDTO.getSexo();
        this.direccion = pacienteDTO.getDireccion();
    }

    public Paciente(Long pacienteId) {
        this.id = pacienteId;
    }
}
