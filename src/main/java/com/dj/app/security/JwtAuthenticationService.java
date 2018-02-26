package com.dj.app.security;

import com.dj.app.domain.Vendor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.mapping.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationService {

	static final long EXPIRATION_TIME = 864_000_000;
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static void addAuthentication (HttpServletRequest httpServletRequest,
										  HttpServletResponse httpServletResponse,
										  UsernamePasswordAuthenticationToken authentication) {
		String userId = (String) authentication.getPrincipal();
		Vendor response = (Vendor) authentication.getDetails();

		Map<String, Object> claims = new HashMap<>();
		/*claims.put("roles", response.getRolesList());*/
		claims.put("userId", userId);
		claims.put("time",System.currentTimeMillis());

		String JWT = Jwts.builder()
				.setSubject("DJ")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.setClaims(claims)
				.compact();

		httpServletRequest.setAttribute("token", JWT);
	}

	static Authentication getAuthentication (HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token);

			List<String> roles = claimsJws.getBody().get("roles", List.class);
			String principal = claimsJws.getBody().get("userId", String.class);
			String credentials = claimsJws.getBody().get("credentials", String.class);;
			return new UsernamePasswordAuthenticationToken(principal, credentials, Collections.emptySet()) ;
		}

		return null;
	}
}
