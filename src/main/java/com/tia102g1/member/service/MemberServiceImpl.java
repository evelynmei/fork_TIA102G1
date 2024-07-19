package com.tia102g1.member.service;

import com.tia102g1.member.dao.MemberDao;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Integer createMember(Member member) {
        return memberDao.createMember(member);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public List<Member> getAll() {
        return memberDao.getAll();
    }

    @Override
    public Integer updateMember(Integer memberId, MemberUpdateDto memberUpdateDto) {

        return memberDao.updateMember(memberId, memberUpdateDto);
    }

    @Override
    public Integer deleteMemberById(Integer memberId) {
        return memberDao.deleteMemberById(memberId);
    }
}
