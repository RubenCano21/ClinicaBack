package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.MedicoHorario;

public interface MedicoHorarioRepository extends JpaRepository<MedicoHorario, Long> {

    boolean existsByConsultorioAndHorario(Consultorio consultorio, Horario horario);
}
