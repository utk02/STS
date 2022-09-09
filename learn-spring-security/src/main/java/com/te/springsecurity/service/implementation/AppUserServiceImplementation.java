package com.te.springsecurity.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.springsecurity.entity.AppUser;
import com.te.springsecurity.repository.AppUserRepository;
import com.te.springsecurity.service.AppUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {
	private final AppUserRepository appUserRepository;

	/*
	 * 1. Find user form database using username
	 * 
	 * 2. If user is present, convert AppUser object into spring security user
	 * object and return User type object
	 * 
	 * 3. Or else throw new UserNameNotFoundException
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> optional = appUserRepository.findByUsername(username);
		if (optional.isPresent()) {
			AppUser appUser = optional.get();
			List<SimpleGrantedAuthority> authorities = appUser.getRoles().stream().map(role -> {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
				return authority;
			}).collect(Collectors.toList());

			return new User(appUser.getUsername(), appUser.getPassword(), authorities);

			/*
			 * OR we can reduce above logic as
			 */
//			AppUser appUser = optional.get();
//			List<SimpleGrantedAuthority> authorities1 = appUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

		}
		throw new UsernameNotFoundException("User with the given username " + username + " does not exist");
	}

}
