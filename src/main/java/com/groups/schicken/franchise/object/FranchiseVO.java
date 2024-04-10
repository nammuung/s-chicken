package com.groups.schicken.franchise.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.util.FileVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
    private String managerId;
    private Long registerId;
    private Long contractId;
    private EmployeeVO manager = new EmployeeVO();
    private FileVO register;
    private FileVO contract;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
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
