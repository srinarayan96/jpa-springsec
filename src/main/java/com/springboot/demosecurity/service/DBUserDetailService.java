package com.springboot.demosecurity.service;

import com.springboot.demosecurity.entity.Member;
import com.springboot.demosecurity.entity.Roles;
import com.springboot.demosecurity.repositories.MemberRepository;
import com.springboot.demosecurity.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final RolesRepository rolesRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No username found"));
        List<Roles> roles = rolesRepository.findByUserId(username);
        Collection<SimpleGrantedAuthority> authorities = roles.stream().map(r1 ->
                new SimpleGrantedAuthority(r1.getRole())).toList();
        return new User(member.getUserId(), member.getPw(), authorities);
    }
}
