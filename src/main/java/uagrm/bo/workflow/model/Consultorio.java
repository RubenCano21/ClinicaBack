package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "consultorios")
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

//    @OneToMany(mappedBy = "consultorio")
//    @JsonIgnore
//    private List<Horario> horarios;
}
