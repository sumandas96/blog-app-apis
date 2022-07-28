//package com.bolappAPI.demo.security;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenHelper {
//	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//
//	private String secret = "jwtTokenKey";
//
//	// retrive username from jwt token
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//
//	// retrive expiration date from token
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//
//	// for retrieving any information from token we will need the secret key
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	}
//
//	// check if the token has expired
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	// generate token for user
//	public String generateToekn(UserDetails userDeatils) {
//		Map<String, Object> claims = new HashMap<>();
//		return doGenerateToken(claims, userDeatils.getUsername());
//	}
//
//	// While Creating the token
//	// 1.Define claims of the token like issuer,Expiration,Subject and ID
//	// 2.Sign the JWT using Hs512 algorithm and secretkey.
//	// compaction of the JWt to a url-safe string
//
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
//				.signWith(SignatureAlgorithm.HS512, secret).compact();
//
//	}
//	
//	//validate token
//	public Boolean validateToken(String token,UserDetails userdetails) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(userdetails.getUsername())  && !isTokenExpired(token));
//	}
//}
