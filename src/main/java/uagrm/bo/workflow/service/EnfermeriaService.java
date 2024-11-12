package uagrm.bo.workflow.service;

import uagrm.bo.workflow.dto.EnfermeriaDTO;
import uagrm.bo.workflow.model.Enfermeria;

import java.util.List;

public interface EnfermeriaService {

    Enfermeria guardar(Enfermeria enfermeria);

    Enfermeria buscarPorId(Long id);

    List<EnfermeriaDTO> listar();

    Enfermeria actualizar(Long id, Enfermeria enfermeria);
}
