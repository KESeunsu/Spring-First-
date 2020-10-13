package spring.springportfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Persistent;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.springportfolio.repository.JdbcTemplateMemberRepository;
import spring.springportfolio.repository.JpaMemberRepository;
import spring.springportfolio.repository.MemberRepository;
import spring.springportfolio.repository.MemoryMemberRepository;
import spring.springportfolio.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration//스프링이 구성되도록?
public class SpringConfig {
    @Persistent//해도 안해도 됨
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    /*private final DataSource dataSource;
        @Autowired
        //생성자 하나일 때는 @Autowired 안써도 스프링 컨테이너가 잘 알아들음
        public SpringConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }*/
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
