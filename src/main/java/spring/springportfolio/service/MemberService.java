package spring.springportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.springportfolio.domain.Member;
import spring.springportfolio.repository.MemberRepository;
import spring.springportfolio.repository.MemoryMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {

    private final MemberRepository repository;//상수
    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /*생성자 만든이유
    MemberService에서 new MemoryMemberRepository객체와 MemberServiceTest'에 new MemoryMemberRepository객테와 서로 다름
    */
    //회원가입
    public Long join(Member member){

        /*중복회원인가 검사(1)
        Optional<Member> result=REPOSITORY.findByName(member.getName());
        result.ifPresent(m ->
                {throw new IllegalStateException("이미 존재하는 회원입니다.");});
        //Optional이라서 쓸수있는 메서드ifPresent->result에 null이 아닌 값이 있다면 exception실행
        */
        //중복회원인가 검사(2)

        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName()).ifPresent(mem ->{throw new IllegalStateException("이미존재하는 회원");});
    }
    /*전체회원 조회*/
    public List<Member> findMembers(){
        return repository.findAll();
    }
    /*회원한명*/
    public Optional<Member> findOne(Long memberId){
        return repository.findById(memberId);
    }

}
