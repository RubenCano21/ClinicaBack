package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Medico;

import java.util.List;

public interface MedicoService {

    List<Medico> listar();
    Medico buscar(Long id);
    Medico guardar(Medico medico);
    void eliminar(Long id);
    Medico actualizar(Medico medico);

    List<Medico> findMedicoByEspecialidad(Long especialidadId);

    //api/pacientes/listar/registrar/eliminar
}
