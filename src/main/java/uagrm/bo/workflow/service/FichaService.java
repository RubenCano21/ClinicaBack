package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Ficha;

import java.util.List;

public interface FichaService {

    Ficha asignarFicha(Long pacienteId, Long especialidadId, Long medicoId,
                       Long horarioId);

    List<Ficha> listarFichas();
}
