package ru.pkozlov.bracketcreator.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import ru.pkozlov.bracketcreator.dto.UserCredentials
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonAuthenticationFilter(
    loginUrl: String,
    private val objectMapper: ObjectMapper
) : AbstractAuthenticationProcessingFilter(loginUrl) {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication =
        objectMapper.readValue(request.inputStream, UserCredentials::class.java)
            .let { user -> UsernamePasswordAuthenticationToken(user.username, user.password) }
            .run(authenticationManager::authenticate)
}