package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;

import java.util.List;

public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {

    List<MedicoEspecialidad> findByMedicoId(Long medicoId);

    boolean existsByMedicoAndEspecialidad(Medico medico, Especialidad especialidad);
}
