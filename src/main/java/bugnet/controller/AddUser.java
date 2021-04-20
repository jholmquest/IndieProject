package bugnet.controller;

import bugnet.entity.Role;
import bugnet.entity.User;
import bugnet.persistence.GenericDao;

import java.util.List;

public class AddUser {

    private GenericDao<User> dao;

    public AddUser() {
        dao = new GenericDao<>(User.class);
    }
    public boolean uniqueUsername(String username) {
        List<User> users = dao.findByPropertyEqual("username", username);

        return users.isEmpty();
    }

    public void add(String username, String password) {
        User newUser = new User(username, password);
        int id = dao.insert(newUser);
        newUser.setId(id);

    }

    public void initializeRole(User newUser) {

        Role initialRole = new Role("user", newUser.getUsername(), newUser);
        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        int roleId = roleDao.insert(initialRole);
        initialRole.setId(roleId);
        newUser.addRole(initialRole);
    }
}
