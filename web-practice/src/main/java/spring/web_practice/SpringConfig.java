package spring.web_practice;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.web_practice.aop.TimeTraceAop;
import spring.web_practice.domain.Member;
import spring.web_practice.repository.*;
import spring.web_practice.service.MemberService;

import javax.sql.DataSource;


//기존에 @Repository, @Service를 사용하는 것과 다르게 직접 빈을 등록해주는 모습
//Controller 경우에는 어쩔 수 없이 @Controller, @Autowired 애노테이션을 사용해서 빈을 등록하고
//의존성 주입을 실행

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
      //JDBC
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
      //JPA
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    //이런 형태로 바로 사용할 시 스프링 데이터 JPA에서 만들어둔 구현체가 자동으로 주입이 된다.
    private final MemberRepository memberRepository;
    //이렇게 할 시 스프링 컨테이너에서 MemberRepository를 찾는다.
    //등록한 게 없어보이지만 SpringDataJpaMemberRepository를 만들어두었기 때문에
    //스프링에서 해당 인터페이스의 구현체를 만든다음 빈에 등록해놓는다.
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    //스프링 데이터 JPA를 사용할 경우 이런 것도 필요없다 따라서
    //구현체를 빈으로 등록하고 자동으로 의존성 주입을 해주기 때문에
//    @Bean
//    public MemberRepository memberRepository() {
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//
//    }




}
