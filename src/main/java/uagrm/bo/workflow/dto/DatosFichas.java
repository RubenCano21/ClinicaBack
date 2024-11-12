package uagrm.bo.workflow.dto;

import lombok.Data;
import uagrm.bo.workflow.model.*;

import java.time.LocalDateTime;

@Data
public class DatosFichas {

    Paciente paciente;
    Especialidad especialidad;
    Medico medico;
    MedicoHorario medicoHorario;
    Horario horario;
    IntervalosHorario intervalosHorario;
    LocalDateTime fecha;
    Integer cantDisponibles;


    public DatosFichas(Ficha ficha) {
        this.paciente = ficha.getPaciente();
        this.especialidad = ficha.getEspecialidad();
        this.medico = ficha.getMedico();
        this.medicoHorario = ficha.getMedicoHorario();
        this.horario = ficha.getMedicoHorario().getHorario();
        this.intervalosHorario = ficha.getIntervaloHorario();
        this.fecha = ficha.getFechaConsulta();
        this.cantDisponibles = ficha.getCantDisponibles();
    }
}
