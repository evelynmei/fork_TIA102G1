package com.tia102g1.member.controller;

import com.tia102g1.member.dto.MemberRegisterRequest;
import com.tia102g1.member.dto.MemberUpdateDto;
import com.tia102g1.member.model.Member;
import com.tia102g1.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/members/register")
    public ResponseEntity<Member> register(@RequestBody
                                           @Valid MemberRegisterRequest memberRegisterRequest) {
        //藉由Keyholder方法返回最新一筆生成的會員id
        Integer memberid = memberService.register(memberRegisterRequest);
        //將生成的會員id利用getmemberId方法返回完整的會員資料
        Member member = memberService.getMemberById(memberid);

        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<Member> getMemberId(@PathVariable Integer memberId) {
        Member member = memberService.getMemberById(memberId);

        if (member != null) {
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAll() {
        List<Member> memberList = memberService.getAll();

        if (memberList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(memberList);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/members/{memberId}")
    public Integer updateMember(@PathVariable Integer memberId,
                                @RequestBody @Valid MemberUpdateDto memberUpdateDto) {
        return memberService.updateMember(memberId, memberUpdateDto);

    }

    @DeleteMapping("/members/{memberId}")
    public Integer deleteMemberById(@PathVariable Integer memberId) {
        return memberService.deleteMemberById(memberId);
    }
}
