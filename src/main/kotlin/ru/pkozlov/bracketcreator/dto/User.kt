package ru.pkozlov.bracketcreator.dto

import ru.pkozlov.bracketcreator.domain.User.Role

data class UserCredentials(
    val username: String,
    val password: String
)

class UserDto(
    val username: String,
    val password: String,
    val roles: Set<Role>,
    val deleted: Boolean
)