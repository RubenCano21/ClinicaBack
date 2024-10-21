package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.Paciente;

import java.util.List;

public interface MedicoService {

    List<Medico> listar();
    Medico buscar(Long id);
    Medico guardar(Medico medico);
    void eliminar(Long id);
    Medico actualizar(Medico medico);

    //api/pacientes/listar/registrar/eliminar
}
