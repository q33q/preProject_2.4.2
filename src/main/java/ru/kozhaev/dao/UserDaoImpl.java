package ru.kozhaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kozhaev.model.Role;
import ru.kozhaev.model.User;
import ru.kozhaev.service.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private RoleService roleService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        Collection<Role> currentRoles = roleService.getAll();
        Collection<Role> userRoles = user.getRoles();
        Collection<Role> rolesForNewUser = new HashSet<>();

        for (Role currentRole : currentRoles) {
            for (Role userRole : userRoles) {
                if (userRole.getName().equals(currentRole.getName())) {
                    rolesForNewUser.add(roleService.getById(currentRole.getId()));
                }
            }
        }

        user.setRoles(rolesForNewUser);
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String firstName) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.firstName = :firstName", User.class);
        return query.setParameter("firstName", firstName).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void update(Long id, User updatedUser) {
        Collection<Role> currentRoles = roleService.getAll();
        Collection<Role> userRoles = updatedUser.getRoles();
        Collection<Role> rolesForNewUser = new HashSet<>();

        for (Role currentRole : currentRoles) {
            for (Role userRole : userRoles) {
                if (userRole.getName().equals(currentRole.getName())) {
                    rolesForNewUser.add(roleService.getById(currentRole.getId()));
                }
            }
        }

        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setRoles(rolesForNewUser);
    }

    @Override
    public void removeById(Long id) {
        entityManager.remove(entityManager.getReference(User.class, id));
    }

}
