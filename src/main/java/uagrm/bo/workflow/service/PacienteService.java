package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> listar();
    Paciente buscar(Long id);
    Paciente guardar(Paciente paciente);
    void eliminar(Long id);
    Paciente actualizar(Paciente paciente);

    //api/pacientes/listar/registrar/eliminar
}