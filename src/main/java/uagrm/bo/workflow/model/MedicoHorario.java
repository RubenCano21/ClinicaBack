package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uagrm.bo.workflow.dto.MedicoHorarioDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medico_horario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"medico_id","consultorio_id", "horario_id"})
})
public class MedicoHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    //@JsonBackReference
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    //@JsonBackReference
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    //@JsonBackReference
    private Horario horario;

    private Integer cantDisponibles;


    public MedicoHorario(MedicoHorarioDTO datosMedicoHorario) {
        this.medico = new Medico(datosMedicoHorario.getMedico().getId());
        this.consultorio = datosMedicoHorario.getConsultorio();
        this.horario = datosMedicoHorario.getHorario();
        this.cantDisponibles = datosMedicoHorario.getCantDisponibles();
    }
}
