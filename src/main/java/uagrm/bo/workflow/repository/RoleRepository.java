package uagrm.bo.workflow.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import uagrm.bo.workflow.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}