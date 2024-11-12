package uagrm.bo.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uagrm.bo.workflow.model.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FichaDTO {


    private Long id;
    private PacienteDTO paciente;
    private EspecialidadDTO especialidad;
    private MedicoDTO medico;
    private MedicoHorarioDTO medicoHorario;
    private IntervaloHorarioDTO intervaloHorario;
    private LocalDateTime fechaConsulta;
    private Integer cantDisponibles;

    public FichaDTO(Ficha ficha) {
        this.id = ficha.getId();
        this.paciente = ficha.getPaciente() != null ? new PacienteDTO(ficha.getPaciente()) : null;
        this.especialidad = (ficha.getEspecialidad() != null) ? new EspecialidadDTO(ficha.getEspecialidad()) : null;
        this.medico = (ficha.getMedico() != null) ? new MedicoDTO(ficha.getMedico()) : null;
        this.medicoHorario = (ficha.getMedicoHorario() != null) ? new MedicoHorarioDTO(ficha.getMedicoHorario()) : null;
        this.intervaloHorario = (ficha.getIntervaloHorario() != null) ? new IntervaloHorarioDTO(ficha.getIntervaloHorario()) : null;
        this.fechaConsulta = ficha.getFechaConsulta();
        this.cantDisponibles = ficha.getCantDisponibles();
    }



}
