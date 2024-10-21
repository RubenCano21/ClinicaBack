package uagrm.bo.workflow.service;

import uagrm.bo.workflow.model.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
