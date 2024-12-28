package spring.web_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.web_practice.domain.Member;
import spring.web_practice.service.MemberService;

import java.util.List;


//스프링 빈에 등록, 스프링 컨테이너가 해당 객체를 관리한다.
//스프링 컨테이너에 등록하면 딱 하나의 객체만이 등록되고 이를 나머지 스프링 빈에서
//다른 부가 기능을 사용할 수 있다.
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    // "/members/new"에 POST 방식으로 넘어갈 때 매핑이 되고 이를 처리하는 메서드가 선언되어 있는 것
    // URL이 같아도 GET이냐 POST냐에 따라서 다르게 매핑시킬 수 있다.
    //Spring이 Post 방식으로 클라이언트로부터 입력받은 값을
    //MemberForm 객체에 name 필드에 자동으로 바인딩 한다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        //회원 가입이 끝난 후 홈화면으로 보내는 커맨드
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);
        return "members/memberList";
    }
}
