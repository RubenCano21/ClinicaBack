package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.IntervalosHorario;

import java.util.List;
import java.util.Optional;

public interface IntervaloHorarioService {

    List<IntervalosHorario> listarIntervalosHorarios();

    Optional<IntervalosHorario> obtenerIntervaloHorarioPorId(Long id);

    IntervalosHorario guardarIntervaloHorario(IntervalosHorario intervaloHorario);

    void eliminarIntervaloHorario(Long id);
}
