package uagrm.bo.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.validation.ExistsByEmail;

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
    @JsonIgnore
    private List<Historial> historiales;

}
