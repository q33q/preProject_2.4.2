package ru.kozhaev.dao;

import ru.kozhaev.model.Role;

import java.util.List;

public interface RoleDao {
    Role save(Role role);

    Role getById(Long id);

    Role getByName(String name);

    List<Role> getAll();

}
