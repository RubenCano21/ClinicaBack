package uagrm.bo.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación ManyToMany con Medico
//    @ManyToMany(mappedBy = "horarios")
//    @JsonIgnore
//    private List<Medico> medicos = new ArrayList<>();

    //@NotNull
    private String dia;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;

    @Min(1) //minimo una ficha se le debe asignar
    private Integer capacidad; // Cantidad de fichas por horario

    // relacion con consultorio (un horario pertenece a un consultorio especifico)♠
//    @ManyToOne
//    @JoinColumn(name = "consultorio_id")
//    private Consultorio consultorio;
}
