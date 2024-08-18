package com.tia102g1.role.dao.impl;

import com.tia102g1.member.rowmapper.MemberRowMapper;
import com.tia102g1.role.dao.RoleDao;
import com.tia102g1.role.model.Role;
import com.tia102g1.role.rowmapper.RoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private RoleRowMapper roleRowMapper;


    @Override
    public Role getRoleByName(String roleName) {
        Map<String, Object> map = new HashMap<>();

        String sql = "SELECT roleid, rolename FROM role WHERE rolename = :roleName";
        map.put("roleName", roleName);

        List<Role> roleList = namedParameterJdbcTemplate.query(sql, map, roleRowMapper);

        return roleList.isEmpty() ? null : roleList.get(0);


    }
}
