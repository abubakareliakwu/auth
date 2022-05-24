package com.auth.security;


import com.auth.repository.UserRepository;
import com.auth.utils.Constants;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
/**
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
	UserRepository userRepository;

    public String getUsernameFromEmail(String email) {
		return getClaimFromEmail(email, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String email) {
		return getClaimFromEmail(email, Claims::getExpiration);
	}

	public <T> T getClaimFromEmail(String email, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromEmail(email);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromEmail(String email) {
		return Jwts.parser()
				.setSigningKey(Constants.SIGNING_KEY)
				.parseClaimsJws(email)
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication authentication) {
		final ArrayList<String> authorities = new ArrayList();

		for (GrantedAuthority auth : authentication.getAuthorities()){
            authorities.add(auth.getAuthority());
        }

		authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(Constants.AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS*36000))
				.compact();
	}

	public Boolean validateToken(String email, UserDetails userDetails) {
		final String username = getUsernameFromEmail(email);
		return (
				username.equals(userDetails.getUsername())
						&& !isTokenExpired(email));
	}

	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(Constants.SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

}*/

