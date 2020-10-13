package spring.springportfolio.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.springportfolio.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //MemberRepository repository=new MemoryMemberRepository(); test용이라 위에꺼 써도 됨 아래는 일반적으로 쓰는 형태

    //test시에 메서드의 순서가 섞여서 삽입된 데이터를 clear해줘야함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring1");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        /*Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);*/

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);


    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member result=repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);

    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        List<Member> result=repository.findAll();

        assertThat(result.size()).isEqualTo(1);
    }



}
