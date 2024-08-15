package com.tia102g1.staff.dao;

import com.tia102g1.role.model.Role;
import com.tia102g1.role.rowmapper.RoleRowMapper;
import com.tia102g1.staff.model.StaffVO;
import com.tia102g1.staff.rowmapper.StaffRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StaffVODaoImpl implements StaffVODao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getRolesByStaffId (阿維新增)
    @Override
    public List<Role> getRolesByStaffId(Integer staffId) {
        Map<String, Object> map = new HashMap<>();
        String sql = "  SELECT role.roleid, role.rolename FROM role\n" +
                "                    JOIN staffhasrole ON role.roleid = staffhasrole.roleid\n" +
                "                    WHERE staffhasrole.staffId = :staffId";

        map.put("staffId", staffId);
        List<Role> roleList = namedParameterJdbcTemplate.query(sql, map, new RoleRowMapper());
        return roleList;
    }

    @Override
    public StaffVO getById(Integer staffId) {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT staffid,password,name,phone,email,employdt,leavedt,status,permission,createdby,datecreated,lastupdatedby,lastupdated \n" +
                "FROM staff WHERE staffid = :staffId";
        map.put("staffId", staffId);
        List<StaffVO> staffList = namedParameterJdbcTemplate.query(sql, map, new StaffRowMapper());
        return staffList.isEmpty() ? null : staffList.get(0);
    }
}
