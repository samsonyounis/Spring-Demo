package com.example.springdemo.Entities;

import jakarta.persistence.*;

@Entity
public class Roles {
    @Id
    private int roleId;
    private String roleName;
    //@ManyToOne()
    //@JoinColumn(name = "user_id")
   // private UserAccount user;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
