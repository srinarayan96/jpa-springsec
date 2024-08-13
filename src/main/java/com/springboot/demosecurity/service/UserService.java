package com.springboot.demosecurity.service;

import com.springboot.demosecurity.entity.Member;
import com.springboot.demosecurity.entity.Roles;
import com.springboot.demosecurity.entity.Roles_PK;
import com.springboot.demosecurity.models.RolesInput;
import com.springboot.demosecurity.models.WebUser;
import com.springboot.demosecurity.repositories.MemberRepository;
import com.springboot.demosecurity.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Roles> findUsersRoles(String username) {
        return rolesRepository.findByUserId(username);
    }

    public void addRoles(String username, RolesInput rolesInput) {
        sameUserCheck(username);
        rolesInput.getRoles().forEach(role -> {
            Roles_PK roles_pk = new Roles_PK(username, role);
            Optional<Roles> r = rolesRepository.findById(roles_pk);
            if(r.isEmpty()) {
                Roles roles = Roles.builder().role(role).userId(username).build();
                rolesRepository.save(roles);
            }
        });
    }

    public void removeRoles(String username, RolesInput rolesInput) {
        sameUserCheck(username);
        rolesInput.getRoles().forEach(role -> {
            Roles_PK roles_pk = new Roles_PK(username, role);
            Optional<Roles> r = rolesRepository.findById(roles_pk);
            if(r.isPresent()) {
                Roles roles = Roles.builder().role(role).userId(username).build();
                rolesRepository.delete(roles);
            }
        });
    }

    private void sameUserCheck(String username){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUser =userDetails.getUsername();
        if(loggedInUser.equalsIgnoreCase(username)){
            throw new AccessDeniedException("User can't alter its own roles");
        }
    }
}
