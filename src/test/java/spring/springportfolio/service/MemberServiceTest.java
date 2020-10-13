package spring.springportfolio.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.springportfolio.domain.Member;
import spring.springportfolio.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        memberRepository =new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
        
    }

    //MemberService에서 new MemoryMemberRepository객체와 MemberServiceTest'에 new MemoryMemberRepository객테와 서로 다름!!

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void join회원가입() {
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

/*
        try {
            memberService.join(member2);
            fail();
            //fail();//위에 IllegalStateException가 아니라서 다음줄에 넘어온다면 실패로 되어라 그래서 이 예외테스트가 실!패!라는것을 알려주기 위해 쓰는 method임
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미존재하는 회원");
        }
*/


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}