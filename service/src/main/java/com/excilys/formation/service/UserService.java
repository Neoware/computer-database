package com.excilys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.formation.entity.User;
import com.excilys.formation.persistence.UserDAO;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRolesString());
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getPassword(), authorities);
			return userDetails;
		}
	}

}
