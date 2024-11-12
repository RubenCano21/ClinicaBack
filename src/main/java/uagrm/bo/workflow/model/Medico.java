package uagrm.bo.workflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer ci;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String sexo;

//    // Relación ManyToMany con Especialidad
//    @OneToMany(mappedBy = "medico")
//    @JsonBackReference
//    private List<MedicoEspecialidad> especialidades = new ArrayList<>();
//
//    // Relación OneToMany con Historial
//    @OneToMany(mappedBy = "medico")
//    @JsonIgnore
//    private List<Historial> historiales = new ArrayList<>();


    public Medico(Long id) {
        this.id = id;
    }


}
