package uagrm.bo.workflow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsPacienteById(Long id);

    boolean existsPacienteByCi(Integer ci);

    boolean existsPacienteByEmail(String email);

    Page<Paciente> findByNombreContaining(String nombreFiltro, Pageable pageable);
}
