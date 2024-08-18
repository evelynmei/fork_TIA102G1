package com.tia102g1.role.model;

import com.tia102g1.member.model.Member;
import com.tia102g1.staff.entity.Staff;
import com.tia102g1.staff.model.StaffVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLEID")
    private Integer roleId;

    @Column(name = "ROLENAME")
    private String roleName;
    // 新增與 Member 的多對多關係
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Member> members = new HashSet<>();

    // 新增與 Staff 的多對多關係
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<StaffVO> staffs = new HashSet<>();


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<StaffVO> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<StaffVO> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", members=" + members +
                ", staffs=" + staffs +
                '}';
    }
}
