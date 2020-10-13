package spring.springportfolio.repository;

import spring.springportfolio.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;
    //jpa쓰러면 EntityManager생성 글고 이것을 주입받아서 씀 내부에서 데이터Connection해주고 데이터 통신도 해줌

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result= em.createQuery("select m from Member m where m.name= :name",Member.class)
                .setParameter("name",name).getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}