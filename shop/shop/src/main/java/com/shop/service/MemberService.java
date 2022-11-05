package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional //에러발생 시 콜백
@RequiredArgsConstructor
public class MemberService {


   private final MemberRepository memberRepository;



    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }


    //가입된 회원일 시 IllegalStateException 예외발생
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }



}
