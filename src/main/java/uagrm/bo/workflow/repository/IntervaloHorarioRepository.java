package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.IntervalosHorario;

@Repository
public interface IntervaloHorarioRepository extends JpaRepository<IntervalosHorario, Long> {



}