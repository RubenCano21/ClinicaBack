package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> listar();
    Paciente buscar(Long id);
    Paciente guardar(Paciente paciente);
    void eliminar(Long id);
    Paciente actualizar(Paciente paciente);

    boolean existsByEmail(String email);

    Optional<Paciente> findPacienteById(Long id);

    //api/pacientes/listar/registrar/eliminar
}
