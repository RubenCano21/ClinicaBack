package uagrm.bo.workflow.servicio;

import uagrm.bo.workflow.entidades.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
