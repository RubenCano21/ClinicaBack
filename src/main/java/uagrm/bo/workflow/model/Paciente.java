package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ci;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String email;
    private String telefono;
    private String sexo;
    private String direccion;

}
