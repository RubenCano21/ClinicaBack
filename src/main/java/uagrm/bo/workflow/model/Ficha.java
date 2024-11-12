package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.dto.DatosFichas;
import uagrm.bo.workflow.dto.FichaDTO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "fichas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"paciente_id", "medico_id", "medico_horario_id", "especialidad_id"})
})
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;


    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "medico_horario_id")
    MedicoHorario medicoHorario;

    @OneToOne
    private IntervalosHorario intervaloHorario;


    private LocalDateTime fechaConsulta;

    private Integer cantDisponibles;


    public Ficha(DatosFichas datos) {
        this.paciente = datos.getPaciente();
        this.especialidad = datos.getEspecialidad();
        this.medico = datos.getMedico();
        this.medicoHorario = datos.getMedicoHorario();
        this.intervaloHorario = datos.getIntervalosHorario();
        this.fechaConsulta = datos.getFecha();
        this.cantDisponibles = datos.getCantDisponibles();
    }
}
