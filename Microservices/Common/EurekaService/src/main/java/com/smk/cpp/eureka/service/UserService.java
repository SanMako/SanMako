/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司         */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.eureka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 *
 * @ClassName: UserService
 * @Author: Mr.Jin-晋
 * @Date: 2021-01-03 19:08
 * @Version: V1.0
 * @Dedscription:
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${spring.security.user.name}")
    private String eurekaUser;

    @Value("${spring.security.user.password}")
    private String eurekaPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        List<GrantedAuthority> roles = new ArrayList<>();
        if (!eurekaUser.equals(username)) {
            throw new UsernameNotFoundException("user not found!");
        }
        userDetails = new User(username, eurekaPassword, roles);
        return userDetails;
    }
}
