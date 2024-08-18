package com.tia102g1.adcvice;

import com.tia102g1.member.model.Member;
import com.tia102g1.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final MemberService memberService;

    public GlobalControllerAdvice(MemberService memberService) {
        this.memberService = memberService;
    }

    @ModelAttribute
    public void addUserAttributes(Model model, Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEMBER"))) {
            // 只有在用戶擁有 MEMBER 角色時才加載會員資訊
            String account = authentication.getName();
            Member member = memberService.getMemberByAccount(account);
            if (member != null) {
                Integer memberId = member.getMemberId();
                model.addAttribute("memberName", member.getName());
                model.addAttribute("memberId", memberId);
                System.out.println("目前登入會員ID為" + memberId);
            }
        }
    }

}
