package ru.pkozlov.bracketcreator.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.pkozlov.bracketcreator.domain.User
import ru.pkozlov.bracketcreator.dto.UserDto
import ru.pkozlov.bracketcreator.exceptions.NotFoundException
import ru.pkozlov.bracketcreator.mapper.mapToUserDetails
import ru.pkozlov.bracketcreator.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)?.mapToUserDetails()
            ?: throw NotFoundException("User with username '$username' not found")

    @Transactional
    fun upsert(user: UserDto): UserDetails = userRepository.findByUsername(user.username)
        ?.apply {
            password = passwordEncoder.encode(user.password)
            roles.addAll(user.roles)
        }
        ?.run(userRepository::save)
        ?.mapToUserDetails()
        ?: User(
            id = userRepository.getNextId(),
            username = user.username,
            password = passwordEncoder.encode(user.password),
            roles = user.roles.toMutableSet()
        )
            .run(userRepository::save)
            .mapToUserDetails()
}