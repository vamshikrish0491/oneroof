package com.acculytixs.wayuparty.security;

 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.util.Constants;


@Component
public class WayupartyAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	private WayupartyPasswordEncoder wayupartyPasswordEncoder;

	 
	
	@Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
  
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(username);
		if(userDTO != null && userDTO.getPassword() != null) {
		
			if(StringUtils.isNotBlank(userDTO.getIsActiveUser()) && userDTO.getIsActiveUser().equalsIgnoreCase(Constants.STRING_Y)) {
				if(wayupartyPasswordEncoder.matches(password, userDTO.getPassword())) {
					if(StringUtils.isNotBlank(userDTO.getIsVerified()) && userDTO.getIsVerified().equalsIgnoreCase("Y")) {
						Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
						setAuths.add(new SimpleGrantedAuthority(userDTO.getUserRole()));
						List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>(setAuths);
						 return new UsernamePasswordAuthenticationToken(username, password,authsList);
					}else {
						throw new BadCredentialsException("email_not_verified");
					}
				}else {
					throw new BadCredentialsException("login_password_error");
				}
			}else {
				throw new BadCredentialsException("login_user_inactive");
			}
		
		}else {
			throw new BadCredentialsException("login_password_error");
		}
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    

}
