package com.tia102g1.role.service;

import com.tia102g1.role.model.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleService {

    Role getRoleByName(String roleName);

}
