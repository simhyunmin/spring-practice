package spring.web_practice.repository;

import spring.web_practice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //value가 null일 때 NullPointerException 발생 방지해줌
    //value가 null일 때 빈 Optional 객체가 생성된다.
    List<Member> findAll();
}
