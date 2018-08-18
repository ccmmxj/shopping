package com.shop.server.dto;

import com.shop.server.model.ManagePersiom;
import com.shop.server.model.ManageRole;
import com.shop.server.model.ManageUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDto implements UserDetails {
    private String sessionId;
    private ManageUser manageUser;
    private List<ManageRole> eduRoles;
    private List<ManageMenuDto> eduMenuList;
    private List<ManagePersiom> eduPersioms;

    public List<ManageRole> getManageRoles() {
        return eduRoles;
    }

    public void setManageRoles(List<ManageRole> eduRoles) {
        this.eduRoles = eduRoles;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
        if(eduPersioms != null){
            List<GrantedAuthority> list = new ArrayList<>();
            eduPersioms.forEach((value)->{
                GrantedAuthority au = new SimpleGrantedAuthority(value.getName());
                list.add(au);
            });
            return list;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return manageUser.getPassword() + "|" + manageUser.getSalt();
    }

    @Override
    public String getUsername() {
        return manageUser.getUserName();
    }

    /*
        *帐号是否不过期，false则验证不通过
        */
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * 帐号是否不锁定，false则验证不通过
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * 凭证是否不过期，false则验证不通过
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * 该帐号是否启用，false则验证不通过
     */
    public boolean isEnabled() {
        return !Boolean.valueOf(manageUser.getIsDeleted()+"");
    }

    public ManageUser getManageUser() {
        return manageUser;
    }

    public void setManageUser(ManageUser manageUser) {
        this.manageUser = manageUser;
    }

    public List<ManageMenuDto> getManageMenuList() {
        return eduMenuList;
    }

    public void setManageMenuList(List<ManageMenuDto> eduMenuList) {
        this.eduMenuList = eduMenuList;
    }

    public List<ManagePersiom> getManagePersioms() {
        return eduPersioms;
    }

    public void setManagePersioms(List<ManagePersiom> eduPersioms) {
        this.eduPersioms = eduPersioms;
    }
}
