package ru.kozhaev.service;

import ru.kozhaev.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role getById(Long id);

    Role getByName(String name);

    List<Role> getAll();
}
