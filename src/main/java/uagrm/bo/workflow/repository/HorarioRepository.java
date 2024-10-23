package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByMedicosAndHoraInicioAndHoraFin(List<Medico> medicos, LocalTime horaInicio, LocalTime horaFin);

}
