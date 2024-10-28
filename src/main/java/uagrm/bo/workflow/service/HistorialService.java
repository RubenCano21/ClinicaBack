package uagrm.bo.workflow.service;

import uagrm.bo.workflow.dto.HistorialDTO;
import uagrm.bo.workflow.model.Historial;

import java.util.List;
import java.util.Optional;

public interface HistorialService {

    Historial crearHistorial(HistorialDTO historialDTO);

    Historial obtenerHistorialPorId(Long id);
    List<Historial> obtenerHistorialesPorPacienteId(Long pacienteId);
    List<Historial> obtenerHistorialesPorMedicoId(Long medicoId);
    List<Historial> obtenerTodosLosHistoriales();
    Historial actualizarHistorial(Long id, Historial historial);
    Historial actualizarHistorialPorMedico(Long historialId, String diagnostico, Long medicoId);
}
