package com.thong.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thong.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
	@Bean
	public AuditorAwareImpl auditorProvider() {
		return new AuditorAwareImpl();
	}

	public static class AuditorAwareImpl implements AuditorAware<String> {

		@Override
		public Optional<String> getCurrentAuditor() {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return Optional.of(username);
		}
	}
}
