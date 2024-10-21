package uagrm.bo.workflow.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import uagrm.bo.workflow.entidades.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
