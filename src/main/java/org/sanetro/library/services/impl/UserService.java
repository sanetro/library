package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IUserDAO;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDAO iUserDAO;

    @Override
    public void init() {
        String[] names = {"Adminowsky", "Userowsky"};
        String[] surnames = {"Kowalska", "Nowak"};
        String[] logins = {"admin", "user"};
        String[] passwords = {"21232f297a57a5a743894a0e4a801fc3", "ee11cbb19052e40b07aac0ca060c23ee"};

        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setName(names[i]);
            user.setSurname(surnames[i]);
            user.setLogin(logins[i]);
            user.setPassword(passwords[i]);
            this.iUserDAO.create(user);
        }
    }
}
