package ru.pkozlov.bracketcreator.mapper

import org.springframework.security.core.userdetails.UserDetails
import ru.pkozlov.bracketcreator.domain.User
import org.springframework.security.core.userdetails.User as UserDto

fun User.mapToUserDetails(): UserDetails = UserDto
    .withUsername(username)
    .password(password)
    .roles(*roles.map { it.name }.toTypedArray())
    .accountLocked(deleted)
    .build()