package com.tia102g1.member.controller;

import com.tia102g1.member.dto.MemberQueryParams;
import com.tia102g1.member.model.Member;
import com.tia102g1.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import util.Page;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Controller
public class ThymeleafMemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/mainPageMember")
    public String members(
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchAccount,
            @RequestParam(defaultValue = "MEMBERID") String orderBy,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "3") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset,
            Model model) {

        // 設置查詢參數
        MemberQueryParams memberQueryParams = new MemberQueryParams();
        memberQueryParams.setSearchName(searchName);
        memberQueryParams.setSearchAccount(searchAccount);
        memberQueryParams.setOrderBy(orderBy);
        memberQueryParams.setSort(sort);
        memberQueryParams.setLimit(limit);
        memberQueryParams.setOffset(offset);

        // 查詢會員列表和總數
        List<Member> memberList = memberService.getAll(memberQueryParams);
        Integer total = memberService.countMember(memberQueryParams);

        // 打印日誌確認 memberList 的內容
        System.out.println("會員列表: " + memberList);

        // 分頁物件設置
        Page<Member> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(memberList);

        // 添加屬性到模型
        model.addAttribute("memberList", memberList);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchAccount", searchAccount);

        return "mainPageMember";
    }
}
