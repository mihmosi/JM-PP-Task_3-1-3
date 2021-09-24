package com.task313.service;

import com.task313.model.Role;

public interface RoleService {
    void create(Role role);
    Role findByRoleName(String roleName);
}
