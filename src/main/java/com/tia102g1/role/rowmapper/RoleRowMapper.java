package com.tia102g1.role.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import com.tia102g1.role.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

        Role role = new Role();
        role.setRoleId(rs.getInt("roleid"));
        role.setRoleName(rs.getString("rolename"));
        return role;
    }
}
