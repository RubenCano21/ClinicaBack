package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación ManyToOne con Medico
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer capacidad; // Cantidad de fichas por horario
}
