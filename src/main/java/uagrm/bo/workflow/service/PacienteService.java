package uagrm.bo.workflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Paciente> listarPagina(Pageable pageable, String nombreFiltro);

    //api/pacientes/listar/registrar/eliminar
}
