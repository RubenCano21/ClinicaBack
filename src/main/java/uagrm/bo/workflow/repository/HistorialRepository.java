package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Historial;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    //consultas aqui
    List<Historial> findByPacienteId(Long pacienteId);

    List<Historial> findByMedicoId(Long medicoId);

    boolean existsHistorialByPacienteId(Long pacienteId);
}
