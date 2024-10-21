package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Especialidad;

import java.util.List;

public interface EspecialidadService {

    List<Especialidad> listar();
    Especialidad buscar(Long id);
    Especialidad guardar(Especialidad especialidad);
    void eliminar(Long id);
    Especialidad actualizar(Especialidad especialidad);

    //List<Especialidad> findMedicoByEspecialidad(Long especialidadId);

    //api/pacientes/listar/registrar/eliminar
}
