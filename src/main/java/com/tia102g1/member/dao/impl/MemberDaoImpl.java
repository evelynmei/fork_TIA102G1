package com.tia102g1.member.dao.impl;


import com.tia102g1.member.dao.MemberDao;
import com.tia102g1.member.dto.MemberRegisterRequest;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;
import com.tia102g1.member.rowmapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createMember(MemberRegisterRequest member) {

        Map<String, Object> map = new HashMap<>();
        String sql = "INSERT INTO member(memberlvid, staffid, account, password, name, birthdt, phone, email, cntcode, distcode,\n" +
                "                   address, accumulate, coinbalance, noshow, cardholder, cardnumber, cardyy, cardmm,\n" +
                "                   cardverifycode, status, blockedtime, blockedreason, createdby, lastupdatedby\n" +
                "                  )\n" +
                "values (:memberlvid, :staffid, :account, :password, :name, :birthdt, :phone, :email, :cntcode, :distcode,\n" +
                "        :address, :accumulate, :coinbalance, :noshow, :cardholder, :cardnumber, :cardyy, :cardmm,\n" +
                "        :cardverifycode,\n" +
                "        :status, :blockedtime, :blockedreason, :createdby, :lastupdatedby)";
        map.put("memberlvid", member.getMemberLvId());
        map.put("staffid", member.getStaffId());
        map.put("account", member.getAccount());
        map.put("password", member.getPassword());
        map.put("name", member.getName());

        map.put("birthdt", member.getBirthDt());
        map.put("blockedtime", member.getBlockedTime());

        map.put("phone", member.getPhone());
        map.put("email", member.getEmail());
        map.put("cntcode", member.getCntCode());
        map.put("distcode", member.getDistCode());
        map.put("address", member.getAddress());
        map.put("accumulate", member.getAccumulate());
        map.put("coinbalance", member.getCoinBalance());
        map.put("noshow", member.getNoShow());
        map.put("cardholder", member.getCardHolder());
        map.put("cardnumber", member.getCardNumber());
        map.put("cardyy", member.getCardYY());
        map.put("cardmm", member.getCardMM());
        map.put("cardverifycode", member.getCardVerifyCode());
        map.put("status", member.getStatus());
        map.put("blockedreason", member.getBlockReason());
        map.put("createdby", member.getCreatedBy());
        map.put("lastupdatedby", member.getLastUpdatedBy());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int id = keyHolder.getKey().intValue();

        return id;


    }

    @Override
    public Member getMemberById(Integer memberId) {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT memberid, memberlvid, staffid, account, password, name, birthdt, phone, \n" +
                "       email, cntcode, distcode, address, accumulate, coinbalance, joindate, noshow, \n" +
                "       cardholder, cardnumber, cardyy, cardmm, cardverifycode, status, blockedtime, \n" +
                "       blockedreason, createdby, datecreated, lastupdatedby, lastupdated FROM member \n" +
                "        where MEMBERID = :memberid";
        map.put("memberid", memberId);
        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        //判斷是否為NULL,查不到該id返回null,查到返回一條數據。
        return memberList.isEmpty() ? null : memberList.get(0);
    }

    @Override
    public Member getMemberByAccount(String account) {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT memberid,memberlvid,staffid,account,password,name,birthdt,phone,email,cntcode,distcode,address,accumulate,\n" +
                "       coinbalance,joindate,noshow,cardholder,cardnumber,cardyy,cardmm,cardverifycode,status,blockedtime,blockedreason,\n" +
                "       createdby,datecreated,lastupdatedby,lastupdated FROM member WHERE ACCOUNT =:account";
        map.put("account", account);
        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        return memberList.isEmpty() ? null : memberList.get(0);
    }

    @Override
    public Member getMemberByUpdatedPasswordMemberId(String password) {
        Map<String,Object> map =new HashMap<>();
        String sql = "SELECT memberid,memberlvid,staffid,account,password,name,birthdt,phone,email,cntcode,distcode,address,accumulate,\n" +
                "       coinbalance,joindate,noshow,cardholder,cardnumber,cardyy,cardmm,cardverifycode,status,blockedtime,blockedreason,\n" +
                "       createdby,datecreated,lastupdatedby,lastupdated FROM member WHERE password =:password";
        map.put("password",password);
        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        return memberList.isEmpty() ? null : memberList.get(0);
    }

    @Override
    public List<Member> getAll() {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT memberid, memberlvid, staffid, account, password, name, birthdt, phone,\n" +
                "       email, cntcode, distcode, address, accumulate, coinbalance, joindate, noshow,\n" +
                "       cardholder, cardnumber, cardyy, cardmm, cardverifycode, status, blockedtime,\n" +
                "       blockedreason, createdby, datecreated, lastupdatedby, lastupdated FROM member";
        //spring jdbc查詢到的結果用一個list接收
        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        //判斷該List是否為null，避免異常例外
        return memberList.isEmpty() ? null : memberList;
    }

    @Override
    public Integer updateMember(Integer memberId, MemberUpdateDto memberUpdateDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = "update member set memberlvid = :memberlvid, staffId = :staffId, password = :password,\n" +
                "                  name = :name, phone = :phone, email = :email, cntCode = :cntCode, distCode = :distCode,\n" +
                "                  address = :address, accumulate = :accumulate, coinBalance = :coinBalance, noShow = :noShow,\n" +
                "                  cardHolder = :cardHolder, cardNumber = :cardNumber, cardYY = :cardYY, cardMM = :cardMM, cardVerifyCode= :cardVerifyCode,\n" +
                "                  status = :status, blockedTime = :blockedTime, BLOCKEDREASON = :blockedReason, lastUpdatedBy = :lastUpdatedBy, lastUpdated = :lastUpdated\n" +
                "                    where memberid = :memberId;";
        map.put("memberId", memberId);

        map.put("memberlvid", memberUpdateDto.getMemberLvId());
        map.put("staffId", memberUpdateDto.getStaffId());
        map.put("password", memberUpdateDto.getPassword());
        map.put("name", memberUpdateDto.getName());
        map.put("blockedTime", memberUpdateDto.getBlockedTime());
        map.put("phone", memberUpdateDto.getPhone());
        map.put("email", memberUpdateDto.getEmail());
        map.put("cntCode", memberUpdateDto.getCntCode());
        map.put("distCode", memberUpdateDto.getDistCode());
        map.put("address", memberUpdateDto.getAddress());
        map.put("accumulate", memberUpdateDto.getAccumulate());
        map.put("coinBalance", memberUpdateDto.getCoinBalance());
        map.put("noShow", memberUpdateDto.getNoShow());
        map.put("cardHolder", memberUpdateDto.getCardHolder());
        map.put("cardNumber", memberUpdateDto.getCardNumber());
        map.put("cardYY", memberUpdateDto.getCardYY());
        map.put("cardMM", memberUpdateDto.getCardMM());
        map.put("cardVerifyCode", memberUpdateDto.getCardVerifyCode());
        map.put("status", memberUpdateDto.getStatus());
        map.put("blockedReason", memberUpdateDto.getBlockReason());
        map.put("lastUpdatedBy", memberUpdateDto.getLastUpdatedBy());
        map.put("lastUpdated", new Date());
        int i = namedParameterJdbcTemplate.update(sql, map);
        return i;
    }

    @Override
    public Integer deleteMemberById(Integer memberId) {
        Map<String, Object> map = new HashMap<>();
        String sql = "DELETE FROM MEMBER where memberId = :memberId";
        map.put("memberId", memberId);
        int i = namedParameterJdbcTemplate.update(sql, map);
        return i;
    }
}
