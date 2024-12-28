package spring.web_practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web_practice.domain.Member;
import spring.web_practice.repository.MemberRepository;
import spring.web_practice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member) {
            validateDuplicateMember(member);  //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    //같은 이름이 있는 중복 회원 x
    //Optional 객체 안에 Member가 있는 것이기 때문에 다양한 메서드 사용 가능
    //ifPresent를 통해 값이 존재하는 값인지 확인 가능
    //값이 존재하면 throw를 통해 메시지 출력
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    //전체 회원 조회
    public List<Member> findMembers() {
            return memberRepository.findAll();
    }

    //회원 한 개 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
