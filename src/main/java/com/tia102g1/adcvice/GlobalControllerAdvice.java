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
    public void addUserAttributes(Model model,Authentication authentication) {
        System.out.println("GlobalControllerAdvice:生效了");

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String account = authentication.getName();
            Member member = memberService.getMemberByAccount(account);
            if (member != null) {
                model.addAttribute("memberName", member.getName());
                model.addAttribute("memberId", member.getMemberId());
            }
        }
    }
}
