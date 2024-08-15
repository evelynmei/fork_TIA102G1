package com.tia102g1.security;

import com.tia102g1.member.dao.MemberDao;
import com.tia102g1.member.model.Member;
import com.tia102g1.staff.dao.StaffDAO;
import com.tia102g1.staff.dao.StaffVODao;
import com.tia102g1.staff.entity.Staff;
import com.tia102g1.staff.model.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.tia102g1.role.model.Role;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private StaffVODao staffVODao;


    @Override
    //此方法是將使用者輸入的帳號密碼，從資料庫查詢這一筆數據，並且回傳給spring security
    //username泛指帳號
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        //從資料庫中查詢Member的數據
        //        Member member = memberDao.getMemberByAccount(username);
        //        if (member == null) {
        //            //自定義異常
        //            // Spring Security 的默認會將 UsernameNotFoundException 包裝成 BadCredentialsException，以防止洩露。
        //            throw new UsernameNotFoundException("找不到該帳號: " + username);
        //        } else {
        //            String memberAccount = member.getAccount();
        //            String memberPassword = member.getPassword();
        //
        //            // 查詢權限部分，根據memberId查詢該會員所擁有哪些role
        //            List<Role> memberRoleList = memberDao.getRolesByMemberId(member.getMemberId());
        //            // 將角色轉換為Spring Security 規定權限控制格式
        //            List<GrantedAuthority> authorities = convertToAuthorities(memberRoleList);
        //
        //            // 轉換成 Spring Security 指定的 User 格式
        //            return new User(memberAccount, memberPassword, authorities);
        //        }
        ////////////////////////////////以下為兩套RBAC模型///////////////////////////////////////////////////////
        // 先從 Member 資料表中查找
        Member member = memberDao.getMemberByAccount(username);
        if (member != null) {
            return userDetails(member.getAccount(), member.getPassword(), memberDao.getRolesByMemberId(member.getMemberId()));
        }

        // 如果找不到 Member，先將username 轉換為 Integer，並從 Staff 資料表中查找
        try {
            Integer staffId = Integer.valueOf(username);
            StaffVO staff = staffVODao.getById(staffId);
            if (staff != null) {
                //將查到的帳號用toString轉回String以便傳入new User中的account參數
                return userDetails(staff.getStaffId().toString(), staff.getPassword(), staffVODao.getRolesByStaffId(staff.getStaffId()));
            }
        } catch (NumberFormatException e) {
            // 如果 username 無法轉換為 Integer，表示這可能不是有效的 Staff 帳號
            throw new UsernameNotFoundException("帳號格式錯誤，請輸入有效的員工編號");
        }

        // 如果都找不到，拋出異常
        throw new UsernameNotFoundException("找不到該帳號: " + username);
    }

    // 將角色轉換為 Spring Security 的 GrantedAuthority 列表並創建 UserDetails
    private UserDetails userDetails(String account, String password, List<Role> roles) {
        List<GrantedAuthority> roleList = convertToAuthorities(roles);
        return new User(account, password, roleList);
    }


    // 將角色轉換為 Spring Security 的 GrantedAuthority 列表
    //GrantedAuthority是Spring中存取權限的介面
    private List<GrantedAuthority> convertToAuthorities(List<Role> roleList) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        //此步驟即是將查詢出來的member所擁有的角色存取到spring security的grantedAuthority中存取控制
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }
}
