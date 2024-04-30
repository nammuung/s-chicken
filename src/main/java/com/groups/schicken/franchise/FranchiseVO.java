package com.groups.schicken.franchise;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.FileVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranchiseVO implements UserDetails {
    private String id;
    private String name;
    private String ownerName;
    private String postCode;
    private String address;
    private String addressDetail;
    private String contactNumber;
    private String registrationNumber;
    private String email;
    private String password;
    private String contractDate;
    private EmployeeVO manager;
    private FileVO register;
    private FileVO contract;
    private String Role="ROLE_FRANCHISE";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.Role));
        return authorities;
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.id;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
