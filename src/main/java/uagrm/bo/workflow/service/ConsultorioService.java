package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.Consultorio;

import java.util.List;

public interface ConsultorioService {

    List<Consultorio> listarConsultorios();

    Consultorio crearConsultorio(Consultorio consultorio);

    Consultorio findConsultorioById(Long id);

    Consultorio actualizarConsultorio(Long id, Consultorio consultorio);

    void eliminarConsultorio(Long id);
}
