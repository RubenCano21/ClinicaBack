package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.MedicoHorario;

import java.util.Optional;

@Repository
public interface MedicoHorarioRepository extends JpaRepository<MedicoHorario, Long> {

    boolean existsByConsultorioAndHorario(Consultorio consultorio, Horario horario);

    Optional<MedicoHorario> findByMedicoIdAndConsultorioIdAndHorarioId(Long medicoId, Long consultorioId, Long horarioId);

//    Integer countByMedicoAndConsultorioAndHorario(Medico medico, Consultorio consultorio, Horario horario);
//
//    // quiero saber un medico cuantos horarios tiene asignados en un dia
//    Integer countByMedicoAndHorario(Medico medico, Horario horario);

}
