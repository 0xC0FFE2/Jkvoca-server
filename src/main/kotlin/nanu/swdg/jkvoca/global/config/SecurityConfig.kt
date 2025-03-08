package nanu.swdg.jkvoca.global.config

import nanu.swdg.jkvoca.global.security.auth.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(
        http: HttpSecurity,
        customAuthenticationProvider: CustomAuthenticationProvider
    ): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/**").permitAll() // TEST: 나중에 무조건 바꿀 것!!!!!!!!!!!!!!!!!!!!!
                    .anyRequest().authenticated()
            }
            .authenticationProvider(customAuthenticationProvider)
            .build()
    }

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().build()
    }
}