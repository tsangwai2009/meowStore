package com.fhk.sample.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fhk.sample.domain.dao.ModeratorDao;
import com.fhk.sample.domain.dao.UserDao;
import com.fhk.sample.domain.entity.ModeratorBean;
import com.fhk.sample.domain.entity.UserBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ModeratorDao moderatorDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	int roleNo = 0;
        String password ="";
        String encode="";
        UserBean user = userDao.findByUsername(name);
        if(user == null){
        	roleNo=1;
        	ModeratorBean moderator = moderatorDao.findByUsername(name);
            if(moderator == null){
            	throw new UsernameNotFoundException(name);
            }
            password = moderator.getPassword();
            encode = new BCryptPasswordEncoder().encode(password);
        }
        else {
            password = user.getPassword();
            encode = new BCryptPasswordEncoder().encode(password);
        }
        Collection<GrantedAuthority> authList = null;
        if(roleNo==0) {authList = getAuthorities(user);}
        else if (roleNo==1) {authList = getAuthorities();}
        return new User(name, encode, true, true, true, true,authList);
    }
    private Collection<GrantedAuthority> getAuthorities(UserBean user){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        String roleName = user.getRole();
        authList.add(new SimpleGrantedAuthority(roleName));
        //也可以继续添加其它角色
        return authList;
    }
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //也可以继续添加其它角色
        return authList;
    }
}
