package ru.kozhaev.dao;

import org.springframework.stereotype.Repository;
import ru.kozhaev.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role save(Role role) {
        List<Role> roles = getAll();
        String name = role.getName();
        boolean containRole = roles.stream().map(Role::getName).anyMatch(name::equals);
        if (!containRole) {
            role = new Role(name);
            entityManager.persist(role);
        }
        return role;
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.name = :name", Role.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role").getResultList();
    }

}
