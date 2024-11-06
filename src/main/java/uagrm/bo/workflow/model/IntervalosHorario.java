package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "intervalos_horario")
public class IntervalosHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "medico_horario_id")
    private MedicoHorario medicoHorario;

    @Enumerated(EnumType.STRING)
    private EstadoIntervalo estado = EstadoIntervalo.LIBRE;

    @OneToOne(mappedBy = "intervaloHorario")
    private Ficha ficha;
} 
