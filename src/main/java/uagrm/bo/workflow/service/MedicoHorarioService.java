package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoHorario;

import java.util.List;

public interface MedicoHorarioService {

    void asignarHorario(Medico medico, Consultorio consultorio, Horario horario, Integer cantDisponibles);

    List<MedicoHorario> listarHorariosMedico();

    void generarIntervalos(MedicoHorario horario, int duracionMin);

    MedicoHorario obtenerMedicoHorario(Long medicoId, Long consultorioId, Long horarioId);
}
