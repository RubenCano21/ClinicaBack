package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Ficha;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    // metodo para contar las fichas asignadas a un medico en un horario especifico
   // int countByMedicoIdAndHorarioId(Long medicoId, Long horarioId);

    boolean existsByPacienteIdAndFechaConsultaBetween(Long paciente_id, LocalDateTime primerHorario, LocalDateTime ultimoHoraio);

    boolean existsByMedicoIdAndFechaConsulta(Long medicoId, LocalDateTime fecha);




}
