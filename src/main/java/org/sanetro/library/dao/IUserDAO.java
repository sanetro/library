package org.sanetro.library.dao;

import org.sanetro.library.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getByLogin(String login);
    List<User> getAll();

}
