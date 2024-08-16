package com.tia102g1.staff.rowmapper;

import com.tia102g1.staff.model.StaffVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<StaffVO> {

    @Override
    public StaffVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StaffVO staff = new StaffVO();
        staff.setStaffId(rs.getInt("STAFFID"));
        staff.setPassword(rs.getString("PASSWORD"));
        staff.setName(rs.getString("NAME"));
        staff.setPhone(rs.getString("PHONE"));
        staff.setEmail(rs.getString("EMAIL"));
        staff.setEmployDt(rs.getDate("EMPLOYDT"));
        staff.setLeaveDt(rs.getDate("LEAVEDT"));
        staff.setStatus(rs.getInt("STATUS"));
        staff.setPermission(rs.getInt("PERMISSION"));
        staff.setCreatedBy(rs.getString("CREATEDBY"));
        staff.setDateCreated(rs.getTimestamp("DATECREATED"));
        staff.setLastUpdatedBy(rs.getString("LASTUPDATEDBY"));
        staff.setLastUpdated(rs.getTimestamp("LASTUPDATED"));
        return staff;

    }
}
