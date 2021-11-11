package ru.kozhaev.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kozhaev.dao.UserDaoImpl;
import ru.kozhaev.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        userDao.removeById(id);
    }

}
