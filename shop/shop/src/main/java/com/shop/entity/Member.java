package com.shop.entity;

import com.shop.constant.Role;
import com.shop.vo.MemberFormVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormVo memberFormVo) {
        Member member = new Member();
        member.setName(MemberFormVo.getName());
        member.setName(MemberFormVo.getEmail());




    }

}
