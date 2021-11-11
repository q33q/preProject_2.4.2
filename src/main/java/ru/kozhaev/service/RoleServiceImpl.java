package ru.kozhaev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kozhaev.dao.RoleDaoImpl;
import ru.kozhaev.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDaoImpl roleDao;

    @Override
    @Transactional
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(Long id) {
        return roleDao.getById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleDao.getAll();
    }

}
