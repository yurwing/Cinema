package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInjection {
    private final RoleService roleService;
    private final UserService userService;

    public DataInjection(RoleService roleService,
                         UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void addRolesAndAdmin() {
        Role roleUser = new Role();
        roleUser.setRoleName(Role.RoleName.USER);
        roleService.add(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setRoleName(Role.RoleName.ADMIN);
        roleService.add(roleAdmin);

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("1234");
        user.setRoles(Set.of(roleAdmin));
        userService.add(user);
    }
}
