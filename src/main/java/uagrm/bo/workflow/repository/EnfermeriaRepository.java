package uagrm.bo.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.model.Enfermeria;

@Repository
public interface EnfermeriaRepository extends JpaRepository<Enfermeria, Long> {

}
