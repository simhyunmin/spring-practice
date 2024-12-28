package spring.web_practice.repository;

import jakarta.persistence.EntityManager;
import spring.web_practice.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //JPA가 insert 쿼리를 생성하고 id까지 전부 부여해준다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //멤버를 조회해줌
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //엔티티를 대상으로 쿼리를 날려서 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
