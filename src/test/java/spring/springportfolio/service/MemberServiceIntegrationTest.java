package spring.springportfolio.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.springportfolio.domain.Member;
import spring.springportfolio.repository.MemberRepository;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//테스트용으로 넣은 데이터를 insert 한것을 다시 rolllback해줌 rollback는 @Test붙은곳에서만 해줌
class MemberServiceIntegrationTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void join() {
        //given
        Member member=new Member();
        member.setName("spring");
        //when
        Long saveId=memberService.join(member);
        //then
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void 중복회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미존재하는 회원");
        //join 메서드 실행하면 이 예외가 터져야 test성공

    }

}