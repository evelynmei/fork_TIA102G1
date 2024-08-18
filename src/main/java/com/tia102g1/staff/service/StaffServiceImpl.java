package com.tia102g1.staff.service;

import com.tia102g1.role.dao.RoleDao;
import com.tia102g1.role.dao.impl.RoleDaoImpl;
import com.tia102g1.role.model.Role;
import com.tia102g1.staff.dao.StaffDAO;
import com.tia102g1.staff.dao.StaffDAOImpl;
import com.tia102g1.staff.dao.StaffVODao;
import com.tia102g1.staff.dao.StaffVODaoImpl;
import com.tia102g1.staff.entity.Staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StaffServiceImpl implements StaffService {

    //新增權限部分
    private RoleDao roleDao;
    private StaffVODao staffDao;

    private StaffDAO dao;

    public StaffServiceImpl() {
        dao = new StaffDAOImpl();
        //新增權限部分
        staffDao = new StaffVODaoImpl();
        roleDao = new RoleDaoImpl();
    }

    @Override
    public Staff addStaff(Staff staff) {
        dao.insert(staff);

//        //新增權限部分
        Role staffRole = roleDao.getRoleByName("ROLE_STAFF");
        staffDao.addRoleForStaffId(staff.getStaffId(), staffRole);
        System.out.println("註冊成功，staffId為 : " + staff.getStaffId() + " 權限為 : " + staffRole.getRoleName());
        return staff;
    }

    @Override
    public Staff updateStaff(Staff staff) {
        dao.update(staff);
        return staff;
    }

    @Override
    public Staff getStaffByStaffId(Integer staffId) {
        Staff staff = dao.getById(staffId);
        return staff;
    }

    @Override
    public List<Staff> getAllStaff(int currentPage) {
        List<Staff> list = dao.getAll(currentPage);
        return list;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> list = dao.getAll();
        return list;
    }

    @Override
    public List<Staff> getStaffByCompositeQuery(Map<String, String[]> map) {
        Map<String, String> query = new HashMap<>();
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();

            if ("action".equals(key)) {
                continue;
            }

            String value = row.getValue()[0];
            if (value.isEmpty() || value == null) {
                continue;
            }
            query.put(key, value);
        }
        System.out.println(query);

        return dao.getByCompositeQuery(query);
    }

    @Override
    public int getPageTotal() {
        int pageQty;
        int pageMax = 5;
        long total = dao.getTotal();
        pageQty = (int) (total % pageMax == 0 ? (total / pageMax) : (total / pageMax + 1));
        return pageQty;
    }
}
