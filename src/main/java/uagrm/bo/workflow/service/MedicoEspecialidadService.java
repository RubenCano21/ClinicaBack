package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;

import java.util.List;

public interface MedicoEspecialidadService {


    List<MedicoEspecialidad> obtenerEspecialidadesPorMedico(Long medicoId);

    MedicoEspecialidad asignarEspecialidadAMedico(Medico medico, Especialidad especialidad);
}
