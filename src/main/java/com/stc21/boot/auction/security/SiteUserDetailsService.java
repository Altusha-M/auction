package com.stc21.boot.auction.security;

import com.stc21.boot.auction.dto.SiteUserPrincipalDto;
import com.stc21.boot.auction.entity.User;
import com.stc21.boot.auction.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// наш кастомный сервис для Spring Security, возвращающий SiteUserPrincipalDto
// на основе пользователя в БД
@Service
public class SiteUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SiteUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SiteUserPrincipalDto(user);
    }
}
