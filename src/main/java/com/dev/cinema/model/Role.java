package com.dev.cinema.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER("USER"), ADMIN("ADMIN");

        private final String role;

        RoleName(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
}
