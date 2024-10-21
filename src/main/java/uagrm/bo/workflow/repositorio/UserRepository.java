package uagrm.bo.workflow.repositorio;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uagrm.bo.workflow.entidades.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}