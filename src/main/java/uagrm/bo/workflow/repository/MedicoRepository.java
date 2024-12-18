package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsMedicoById(Long id);

    boolean existsMedicoByCi(Integer ci);

    boolean existsMedicoByEmail(String email);


}
