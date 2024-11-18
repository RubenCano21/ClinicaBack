package uagrm.bo.workflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uagrm.bo.workflow.dto.MedicoEspecialidadDTO;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;

import java.util.List;

public interface MedicoEspecialidadService {


    List<MedicoEspecialidad> listarMedicosYEspecialidades();

    List<MedicoEspecialidadDTO> obtenerEspecialidadesPorMedico(Long medicoId);

    List<MedicoEspecialidadDTO> obtenerMedicosPorEspecialidad(Long especialidadId);

    void asignarEspecialidadAMedico(Medico medico, Especialidad especialidad);

    Page<MedicoEspecialidad> listarPaginaMedicoEspecialidad(Pageable pageable, String nombreFiltro);
}
