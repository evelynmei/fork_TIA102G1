package com.tia102g1.staff.dao;

import com.tia102g1.role.model.Role;
import com.tia102g1.staff.model.StaffVO;

import java.util.List;

public interface StaffVODao {

    /**
     * 查詢staff的role (阿維)
     *
     * @param staffId
     * @return
     */
    List<Role> getRolesByStaffId(Integer staffId);

    /**
     * 查詢員工的帳號id
     *
     * @param staffId
     * @return
     */
    StaffVO getById(Integer staffId);

    /**
     * 藉由員工ID新增權限
     * @param staffId
     * @param role
     * @return
     */
    void addRoleForStaffId(Integer staffId, Role role);

}
