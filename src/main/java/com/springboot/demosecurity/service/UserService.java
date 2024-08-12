package com.springboot.demosecurity.service;

import com.springboot.demosecurity.entity.Member;
import com.springboot.demosecurity.entity.Roles;
import com.springboot.demosecurity.models.WebUser;
import com.springboot.demosecurity.repositories.MemberRepository;
import com.springboot.demosecurity.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public String saveUser(WebUser webUser) {
        Optional<Member> memberOptional = memberRepository.findById(webUser.getUserName());
        if(memberOptional.isPresent()){
            return "already exists";
        } else {
            Member member = Member.builder().userId(webUser.getUserName()).pw(passwordEncoder.encode(webUser.getPassword()))
                    .active(1).build();
            Roles roles = Roles.builder()
                    .userId(webUser.getUserName())
                    .role("ROLE_EMPLOYEE")
                    .build();
            memberRepository.save(member);
            rolesRepository.save(roles);
            return "User registered Successfully";
        }
    }

}
