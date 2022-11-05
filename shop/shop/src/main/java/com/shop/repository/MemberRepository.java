package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //회원가입 시 중복회원이 검사하기 위한 쿼리메소드
    Member findByEmail(String email);

}
