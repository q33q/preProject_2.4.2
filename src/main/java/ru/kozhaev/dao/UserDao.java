package ru.kozhaev.dao;


import ru.kozhaev.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    User getById(Long id);

    User getByName(String name);

    List<User> getAll();

    void update(Long id, User user);

    void removeById(Long id);

}
