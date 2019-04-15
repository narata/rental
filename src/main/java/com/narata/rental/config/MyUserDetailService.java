package com.narata.rental.config;

import com.narata.rental.entity.UserEntity;
import com.narata.rental.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author narata
 * @since 2019/03/26
 */

@Service
public class MyUserDetailService implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public MyUserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("user"));
        return new User(user.getUsername(), user.getPassword(), simpleGrantedAuthorityList);
    }
}
