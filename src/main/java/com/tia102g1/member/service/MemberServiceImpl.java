package com.tia102g1.member.service;

import com.tia102g1.member.dao.MemberDao;
import com.tia102g1.member.dto.MemberLoginRequest;
import com.tia102g1.member.dto.MemberRegisterRequest;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class MemberServiceImpl implements MemberService {
    //添加LOG，制式化寫法
    private final static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override

    public Integer register(MemberRegisterRequest memberRegisterRequest) {
        //為什麼service取名register而不是createMember?，因service層會添加其他額外判斷是否register，而不單單只是create而已
        //檢查帳號
        //註冊account不可重複，先藉由getMemberByAccount判斷是否有查詢到該帳號，有查到則代表該帳號已經註冊過
        Member member = memberDao.getMemberByAccount(memberRegisterRequest.getAccount());

        //判斷不是null，代表該帳號已經註冊過了
        if (member != null) {
            log.warn("該帳號已經被註冊");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //使用 BCrypt 加密密碼
        String hashedPassword = passwordEncoder.encode(memberRegisterRequest.getPassword());
        //將加密過後的密碼set到該會員資料
        memberRegisterRequest.setPassword(hashedPassword);
        //創建帳號
        return memberDao.createMember(memberRegisterRequest);
    }

    @Override
    public Member login(MemberLoginRequest memberLoginRequest) {
        Member member = memberDao.getMemberByAccount(memberLoginRequest.getAccount());

        //登入失敗情況
        if (member == null) {
            log.warn("該帳號不存在");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return member;
        //將使用者輸入的密碼先利用MD5加密，再將此密碼和資料庫的會員密碼比對是否相同
//        String hashedPassword = DigestUtils.md5DigestAsHex(memberLoginRequest.getPassword().getBytes());
//
//        //判斷該帳號所輸入的密碼是否正確
//        if (member.getPassword().equals(hashedPassword)) {
//            return member;
//        } else {
//            log.warn("輸入的密碼錯誤");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
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
        Member member = memberDao.getMemberByUpdatedPasswordMemberId(memberUpdateDto.getPassword());

        //將更新過後的密碼加密
        String hashedPassword = passwordEncoder.encode(memberUpdateDto.getPassword());
        memberUpdateDto.setPassword(hashedPassword);

        return memberDao.updateMember(memberId, memberUpdateDto);
    }

    @Override
    public Integer deleteMemberById(Integer memberId) {
        return memberDao.deleteMemberById(memberId);
    }
}
