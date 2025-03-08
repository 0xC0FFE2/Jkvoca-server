package nanu.swdg.jkvoca.global.config

import jakarta.servlet.http.HttpServletResponse
import nanu.swdg.jkvoca.global.security.auth.AuthProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authProvider: AuthProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/v1/auth/token", "/v1/vocab/read", "/v1/vocab/read/**", "/v1/vocab/info/**")
                    .permitAll()
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exception ->
                exception.authenticationEntryPoint { request, response, authException ->
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.contentType = "application/json"
                    response.characterEncoding = "UTF-8"
                    response.writer.write("{\"message\":\"Unauthorized\"}")
                }
            }
            .authenticationProvider(authProvider)
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .build()
    }
}