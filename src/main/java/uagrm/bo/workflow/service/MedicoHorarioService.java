package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;

public interface MedicoHorarioService {

    void asignarHorario(Medico medico, Consultorio consultorio, Horario horario);
}
