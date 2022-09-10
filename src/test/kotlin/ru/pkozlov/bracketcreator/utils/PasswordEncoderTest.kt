package ru.pkozlov.bracketcreator.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class PasswordEncoderTest {
    private val encoder: PasswordEncoder = BCryptPasswordEncoder()

    @Test
    fun encode() {
        val rowPassword = "7861paSDrt"

        val encodedPassword: String = encoder.encode(rowPassword)

        println(encodedPassword)
    }

    @Test
    fun verifyPassword() {
        val rowPassword = "admin"
        val encodedPassword = "\$2a\$10\$a71WaoDjfNMcScQqYFxpiu0rwKWgNIlM82h4/z7HHhZX7Jj6lEP.W"

        val isPasswordsMatch: Boolean = encoder.matches(rowPassword, encodedPassword)

        Assertions.assertThat(isPasswordsMatch).isTrue
    }
}