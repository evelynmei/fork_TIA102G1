package com.tia102g1.member.dao;

import com.tia102g1.member.dto.MemberRegisterRequest;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;

import java.util.List;


public interface MemberDao {
    /**新增一筆會員的方法
     *
     * @param memberRegisterRequest 前端傳來的要新建的會員資料
     * @return 返回的是最新會員的ID
     */
    Integer createMember(MemberRegisterRequest memberRegisterRequest);

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
     * 藉由帳號查詢會員ID的方法
     * @param account 要查詢的帳號
     * @return 該帳號的會員資料，沒有該帳號則返回null
     */
    Member getMemberByAccount(String account);

    /**
     * 更新某會員ID資料的方法
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
