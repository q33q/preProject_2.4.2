package ru.kozhaev.Util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kozhaev.model.Role;
import ru.kozhaev.model.User;
import ru.kozhaev.service.RoleService;
import ru.kozhaev.service.UserService;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private final RoleService roleService;

    public SetupDataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        final Role userRole = createRoleIfNotFound("ROLE_USER");

        createUserIfNotFound(
                "q",
                "Admin",
                (byte) 1,
                "admin@admin.com",
                "q",
                new HashSet<>(Collections.singletonList(roleService.getByName("ROLE_ADMIN"))));

        createUserIfNotFound(
                "w",
                "User",
                (byte) 2,
                "user@user.com",
                "w",
                new HashSet<>(Collections.singletonList(roleService.getByName("ROLE_USER"))));
    }

    @Transactional
    protected Role createRoleIfNotFound(final String name) {
        List<Role> roles = roleService.getAll();
        boolean containRole = roles.stream().map(Role::getName).anyMatch(name::equals);
        Role role = null;
        if (!containRole) {
            role = new Role(name);
            role = roleService.save(role);
        }
        return role;
    }

    @Transactional
    protected void createUserIfNotFound(String firstName, String lastName, Byte age, String email, String password, Collection<Role> roles) {
        List<User> users = userService.getAll();
        boolean containUser = users.stream().map(User::getFirstName).anyMatch(firstName::equals);
        User user = null;
        if (!containUser) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setPassword(password);
            user.setEmail(email);
        }
        Objects.requireNonNull(user).setRoles(roles);
        userService.save(user);
    }

}
