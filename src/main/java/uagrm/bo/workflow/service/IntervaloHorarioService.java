package uagrm.bo.workflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uagrm.bo.workflow.dto.IntervaloHorarioDTO;
import uagrm.bo.workflow.model.IntervalosHorario;

import java.util.List;
import java.util.Optional;

public interface IntervaloHorarioService {

    List<IntervaloHorarioDTO> listarIntervalosHorarios();

    Optional<IntervalosHorario> obtenerIntervaloHorarioPorId(Long id);

    IntervalosHorario guardarIntervaloHorario(IntervalosHorario intervaloHorario);

    void eliminarIntervaloHorario(Long id);

    Page<IntervalosHorario> listarIntervalosHorarios(Pageable pageable, String nombreFiltro);
}
