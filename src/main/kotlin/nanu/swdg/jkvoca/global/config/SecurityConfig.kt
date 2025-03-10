package nanu.swdg.jkvoca.global.config

import jakarta.servlet.http.HttpServletResponse
import nanu.swdg.jkvoca.global.security.auth.AuthProvider
import nanu.swdg.jkvoca.global.security.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authProvider: AuthProvider,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val corsConfigurationSource: CorsConfigurationSource
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .securityMatcher("/**")
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/v1/auth/token", "/v1/vocab/read", "/v1/vocab/read/**", "/v1/vocab/info/**" , "/v1/words/vocab/list/**", "/v1/vocab/search/**", "/v2/classroom/info/**")
                    .permitAll()
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exception ->
                exception.authenticationEntryPoint { request, response, authException ->
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                }
            }
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .httpBasic { it.disable() }
            .formLogin { it.disable() }

        http.cors { it.configurationSource(corsConfigurationSource) }

        return http.build()
    }
}