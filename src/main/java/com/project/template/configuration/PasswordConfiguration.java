package com.project.template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Map;

@Configuration
public class PasswordConfiguration {

	/**
	 * 可修改的加密算法配置
	 *
	 * @return 密码加密器
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		var idForEncode = "bcrypt";
		var encoders = Map.of(
				"bcrypt", new BCryptPasswordEncoder(),
				"pbkdf2", new Pbkdf2PasswordEncoder(),
				"scrypt", new SCryptPasswordEncoder()
		);
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}
}
