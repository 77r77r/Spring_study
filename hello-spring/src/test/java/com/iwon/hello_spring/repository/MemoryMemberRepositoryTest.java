package com.iwon.hello_spring.repository;

import com.iwon.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 메서드 실행이 끝날 때마다 동작하는 메서드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("김고객");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);  // JUnit
        assertThat(member).isEqualTo(result);       // AssertJ
        //assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("김고객");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("민티아");
        repository.save(member2);

        Member result = repository.findByName("김고객").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("김고객");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("민티아");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
