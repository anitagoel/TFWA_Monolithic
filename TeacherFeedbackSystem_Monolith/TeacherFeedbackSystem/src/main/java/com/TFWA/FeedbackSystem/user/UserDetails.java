package com.ishitwa.FeedbackSystem.user;

import com.ishitwa.FeedbackSystem.user.Student;
import com.ishitwa.FeedbackSystem.user.Teacher;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private String username;
    private String password;
    private boolean isVerified;
    private List<GrantedAuthority> authorities;
    public UserDetails(Teacher user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.isVerified=user.isEnabled();
        this.authorities= Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public UserDetails(Student student){
        this.username= student.getUsername();
        this.password= student.getPassword();
        this.isVerified= student.isEnabled();
        this.authorities= Arrays.stream(student.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isVerified;
    }
}
