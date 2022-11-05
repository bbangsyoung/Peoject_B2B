package com.shop.entity;

import com.shop.constant.Role;
import com.shop.vo.MemberFormVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //이메일로 구분할 것이기 때문에 동일한 값이 DB에 들어올 수 없도록 유니크 속성지정
    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    //멤버생성 메소드
    public static Member createMember(MemberFormVo memberFormVo, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormVo.getName());
        member.setEmail((memberFormVo.getEmail()));
        member.setAddress(memberFormVo.getAddress());

        //시큐리티 설정클래스에 등록한 빈을 파라미터르 넘겨 비밀번호를 암호화
        String password = passwordEncoder.encode(memberFormVo.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
