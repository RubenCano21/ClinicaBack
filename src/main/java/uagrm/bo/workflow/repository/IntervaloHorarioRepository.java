package uagrm.bo.workflow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.IntervalosHorario;
import uagrm.bo.workflow.model.MedicoHorario;

import java.util.List;

@Repository
public interface IntervaloHorarioRepository extends JpaRepository<IntervalosHorario, Long> {


    List<IntervalosHorario> findByMedicoHorario(MedicoHorario horario);

    Page<IntervalosHorario> findByMedicoHorario(MedicoHorario medicoHorario, Pageable pageable);

    Page<IntervalosHorario> findByMedicoHorarioContains(String nombreFiltro, Pageable pageable);
}
