package com.shop.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.shop.server.dto.ManageMenuDto;
import com.shop.server.dto.UserDto;
import com.shop.server.mapper.ManageMenuMapper;
import com.shop.server.mapper.ManagePersiomMapper;
import com.shop.server.mapper.ManageRoleMapper;
import com.shop.server.mapper.ManageUserMapper;
import com.shop.server.model.ManagePersiom;
import com.shop.server.model.ManageRole;
import com.shop.server.model.ManageUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(ManageUserServiceImpl.class);

    @Autowired
    private ManageUserMapper eduUserMapper;
    @Autowired
    private ManagePersiomMapper eduPersiomMapper;
    @Autowired
    private ManageRoleMapper eduRoleMapper;
    @Autowired
    private ManageMenuMapper eduMenuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = new UserDto();
        ManageUser user = eduUserMapper.selectByUsername(username);
        logger.info("username:==================={}============",username);
        if (user != null) {
            List<ManagePersiom> permissions = eduPersiomMapper.selectByUserId(user.getId());
            List<ManageRole> roles = eduRoleMapper.selectByUserId(user.getId());
            List<ManageMenuDto> menus = eduMenuMapper.selectByUserId(user.getId());
            menus.forEach((value)->{
                value.setChildManageMenu(eduMenuMapper.selectByPid(value.getId()));
            });
            userDto.setManageUser(user);
            userDto.setManagePersioms(permissions);
            userDto.setManageMenuList(menus);
            userDto.setManageRoles(roles);
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (ManagePersiom permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
//
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            }
//            return User.withUsername(user.getUserName()).password(user.getPassword())
//                    .roles(roles.stream().map( (value)-> value.getName()).collect(Collectors.toList()).toArray(new String[0]))
//                    .authorities(permissions.stream().map(value -> value.getName()).collect(Collectors.toList()).toArray(new String[0])).build();
            logger.info("userDto:==================={}============", JSON.toJSONString(userDto));
            return userDto;
        } else {
            throw new UsernameNotFoundException("user: " + username + " do not exist!");
        }
    }
}
