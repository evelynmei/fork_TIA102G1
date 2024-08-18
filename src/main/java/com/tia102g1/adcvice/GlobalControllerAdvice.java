package com.tia102g1.adcvice;

import com.tia102g1.member.model.Member;
import com.tia102g1.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
        if (authentication != null) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEMBER"))) {
                // 處理一般帳號登入
                String account = authentication.getName();
                Member member = memberService.getMemberByAccount(account);
                if (member != null) {
                    Integer memberId = member.getMemberId();
                    model.addAttribute("memberName", member.getName());
                    model.addAttribute("memberId", memberId);
                    System.out.println("目前登入會員ID為" + memberId);
                }
            } else if (authentication.getPrincipal() instanceof OAuth2User) {
                // 處理第三方登入
                OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
                String name = oauthUser.getAttribute("name");
                String providerId = oauthUser.getName(); // 取得 providerId
                model.addAttribute("oauth2MemberName", name);
                model.addAttribute("providerId", providerId);
                System.out.println("第三方登入的 providerId 為" + providerId);
            }
        }
    }
}
