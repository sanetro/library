package org.sanetro.library.services;

public interface IAuthenticationService {
    void login(String login, String password);
    void logout();
}
