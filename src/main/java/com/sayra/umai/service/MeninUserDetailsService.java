package com.sayra.umai.service;

import com.sayra.umai.model.entity.user.Role;
import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.repo.UserEntityRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeninUserDetailsService implements UserDetailsService {
    private final UserEntityRepo userRepository;

    public MeninUserDetailsService(UserEntityRepo userRepository) {
        this.userRepository = userRepository;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found"));

        // Измените вывод ролей на их имена
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName) // Получаем имя роли
                .toList();

        System.out.println("User  found: " + user.getUsername() + " with roles: " + roleNames);

        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
}
