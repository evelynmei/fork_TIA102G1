package com.tia102g1.member.service;

import com.tia102g1.member.dto.MemberLoginRequest;
import com.tia102g1.member.dto.MemberRegisterRequest;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;

import java.util.List;


public interface MemberService {
    /**
     * 新增一筆會員資料的方法
     * @param memberRegisterRequest 由前端發送過來的參數
     */
    Integer register(MemberRegisterRequest memberRegisterRequest);
    /**
     * 會員登入的方法
     * @param memberLoginRequest 會員登入的帳密
     * @return 返回登入的帳號
     */
    Member login(MemberLoginRequest memberLoginRequest);
    /**
     * 藉由會員ID查詢會員的方法
     * @param memberId 要查詢的會員ID
     * @return 返回該會員的相關資料
     */
    Member getMemberById(Integer memberId);
    /**
     * 查詢所有會員的方法
     * @return 返回一個包含所有會員的List
     */
    List<Member> getAll();
    /**
     * 更新某會員ID地址的方法
     *
     */
    Integer updateMember(Integer memberId, MemberUpdateDto memberUpdateDto);
    /**
     * 刪除某會員ID的方法
     * @param memberId 要刪除的會員ID
     *
     */
    Integer deleteMemberById(Integer memberId);
}
