package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
