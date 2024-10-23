package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Horario;

import java.util.List;

public interface HorarioService {

    Horario crearHorario(Horario horario);

    List<Horario> obtenerHorariosPorMedico(Long medicoId);

    void eliminarHorario(Long id);

    List<Horario> listar();
}
