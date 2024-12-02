package com.project.template.common.utils;

import com.project.template.common.constants.SystemConstant;
import com.project.template.business.domain.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

public class JwtUtils {

	private static final String JWT_KEY = "2tQq2AeomJX1sCPBON5jRdkpET5DNN9Hpzjv888xTBOP83LS2qbCoq0vuYTwLTIqXL3GUOvEyUrda7B1zKe+Hw==";
	private static final String JWT_REFRESH_KEY = "AgbMd/alAL6gUAPE55xh9AgcH+Aq3fyy8X0TKy02L49O7mmLAzgF2D7TsEKfB/VtePujY6sQ4SCzC/sovDCFpg==";

	/**
	 * 用于签名的访问令牌的密钥
	 */
	public static final Key KEY = new SecretKeySpec(Base64.getDecoder().decode(JWT_KEY), "HmacSHA512");

	/**
	 * 用于签名的刷新令牌的密钥
	 */
	public static final Key REFRESH_KEY = new SecretKeySpec(Base64.getDecoder().decode(JWT_REFRESH_KEY), "HmacSHA512");

	/**
	 * 根据用户信息生成Jwt
	 *
	 * @param user 用户信息
	 * @return Jwt
	 */
	public static String createAccessToken(User user) {
		return createJwtToken(user, SystemConstant.ACCESS_TOKEN_EXPIRE_TIME, KEY);
	}

	/**
	 * 根据用户信息生成Jwt刷新
	 *
	 * @param user 用户信息
	 * @return 刷新token
	 */
	public static String createRefreshToken(User user) {
		return createJwtToken(user, SystemConstant.REFRESH_TOKEN_EXPIRE_TIME, REFRESH_KEY);
	}

	/**
	 * 根据刷新token重新生成Jwt
	 *
	 * @param refreshToken 刷新token
	 * @return Jwt
	 */
	public static Optional<String> createAccessTokenWithRefreshToken(String refreshToken) {
		return parseClaims(refreshToken, REFRESH_KEY)
				.map(claims -> Jwts.builder()
						.setClaims(claims)
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + SystemConstant.ACCESS_TOKEN_EXPIRE_TIME))
						.signWith(KEY, SignatureAlgorithm.HS512)
						.compact()
				);
	}

	/**
	 * 根据刷新token重新生成刷新token
	 *
	 * @param refreshToken 刷新token
	 * @return 刷新token
	 */
	public static Optional<String> createRefreshTokenWithRefreshToken(String refreshToken) {
		return parseClaims(refreshToken, REFRESH_KEY)
				.map(claims -> Jwts.builder()
						.setClaims(claims)
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + SystemConstant.REFRESH_TOKEN_EXPIRE_TIME))
						.signWith(REFRESH_KEY, SignatureAlgorithm.HS512)
						.compact()
				);
	}

	/**
	 * 校验Jwt
	 *
	 * @param token Jwt
	 * @return 是否合规
	 */
	public static boolean validateAccessToken(String token) {
		return validateToken(token, KEY, true);
	}

	/**
	 * 校验Jwt(不论是否过期)
	 *
	 * @param token Jwt
	 * @return 是否合规
	 */
	public static boolean validateAccessTokenWithoutExpiration(String token) {
		return validateToken(token, KEY, false);
	}

	/**
	 * 校验刷新token
	 *
	 * @param token 刷新token
	 * @return 是否合规
	 */
	public static boolean validateRefreshToken(String token) {
		return validateToken(token, REFRESH_KEY, true);
	}

	/**
	 * 校验刷新token(不论是否过期)
	 *
	 * @param token 刷新token
	 * @return 是否合规
	 */
	public static boolean validateRefreshTokenWithoutExpiration(String token) {
		return validateToken(token, REFRESH_KEY, false);
	}

	/**
	 * 校验Jwt
	 *
	 * @param token            Jwt
	 * @param key              签名使用的 key
	 * @param isExpiredInvalid 是否不论过期状态
	 * @return 是否合规
	 */
	public static boolean validateToken(String token, Key key, boolean isExpiredInvalid) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parse(token);
			return true;
		} catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			if (e instanceof ExpiredJwtException) {
				return !isExpiredInvalid;
			}
			return false;
		}
	}

	/**
	 * 构建Jwt载荷
	 *
	 * @param token Jwt
	 * @return Jwt载荷
	 */
	public static Optional<Claims> parseAccessTokenClaims(String token) {
		return parseClaims(token, KEY);
	}

	/**
	 * 构建Jwt载荷
	 *
	 * @param token Jwt
	 * @return Jwt载荷
	 */
	public static Optional<Claims> parseRefreshTokenClaims(String token) {
		return parseClaims(token, REFRESH_KEY);
	}

	/**
	 * 构建Jwt载荷
	 *
	 * @param token token
	 * @param key   签名使用的 key
	 * @return Jwt载荷
	 */
	public static Optional<Claims> parseClaims(String token, Key key) {
		try {
			var claims = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
			return Optional.of(claims);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * 根据用户信息生成一个Jwt
	 *
	 * @param user         用户信息
	 * @param timeToExpire 毫秒单位的失效时间
	 * @param key          签名使用的 key
	 * @return Jwt
	 */
	public static String createJwtToken(User user, long timeToExpire, Key key) {
		var now = System.currentTimeMillis();
		return Jwts.builder()
				.setId(String.valueOf(user.getId()))
				.claim("username", user.getUsername())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + timeToExpire))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
}
