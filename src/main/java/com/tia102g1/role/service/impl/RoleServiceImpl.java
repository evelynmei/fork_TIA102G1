package com.tia102g1.role.service.impl;

import com.tia102g1.role.dao.RoleDao;
import com.tia102g1.role.model.Role;
import com.tia102g1.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }
}
