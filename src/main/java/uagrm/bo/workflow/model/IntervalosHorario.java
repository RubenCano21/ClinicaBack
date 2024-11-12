package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.dto.IntervaloHorarioDTO;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "intervalos_horario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"medico_horario_id", "hora_inicio", "hora_fin"})
})
public class IntervalosHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "medico_horario_id")
    //@JsonIgnore
    private MedicoHorario medicoHorario;

    @Enumerated(EnumType.STRING)
    private EstadoIntervalo estado = EstadoIntervalo.LIBRE;

    @OneToOne(mappedBy = "intervaloHorario")
    //@JsonIgnore
    private Ficha ficha;

    public IntervalosHorario(IntervaloHorarioDTO horarioDTO){
        this.id = horarioDTO.getId();
        this.horaInicio = LocalTime.parse(horarioDTO.getHoraInicio());
        this.horaFin = LocalTime.parse(horarioDTO.getHoraFin());
        this.estado = EstadoIntervalo.valueOf(horarioDTO.getEstado());
        this.ficha = new Ficha();
        this.ficha.setId(horarioDTO.getFichaId());
    }
} 
