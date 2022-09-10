package ru.pkozlov.bracketcreator.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import ru.pkozlov.bracketcreator.security.JsonAuthenticationFilter
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val objectMapper: ObjectMapper
) {
    companion object {
        const val LOGIN_URL = "/login"
        const val LOGOUT_URL = "/logout"
        const val AUTH_COOKIE_NAME = "JSESSIONID"
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http.authorizeRequests()
            .antMatchers("/login", "/logout").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().disable()
            .csrf().disable()
            .addFilterBefore(
                authenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .logout { security ->
                security
                    .logoutUrl(LOGOUT_URL)
                    .invalidateHttpSession(true)
                    .deleteCookies(AUTH_COOKIE_NAME)
                    .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
            }
            .build()

    @Bean
    fun authenticationFilter(): JsonAuthenticationFilter = JsonAuthenticationFilter(
        loginUrl = LOGIN_URL,
        objectMapper = objectMapper
    ).apply {
        setAuthenticationManager(authenticationConfiguration.authenticationManager)
        setAuthenticationSuccessHandler { _, response, _ -> response.status = HttpServletResponse.SC_OK }
        setAuthenticationFailureHandler { _, response, _ -> response.status = HttpServletResponse.SC_UNAUTHORIZED }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()
}