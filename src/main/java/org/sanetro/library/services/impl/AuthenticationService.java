package org.sanetro.library.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.sanetro.library.dao.IUserDAO;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IAuthenticationService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void login(String login, String password) {
        Optional<User> userBox = this.userDAO.getByLogin(login);
        System.out.println(DigestUtils.md5Hex(password));
        if(userBox.isPresent() &&
                userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setLoggedUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    public String checkSessionBeforeRedirect(String view) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/";
        }
        return view;
    }
}
