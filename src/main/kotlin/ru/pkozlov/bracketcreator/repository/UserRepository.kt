package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.User

interface UserRepository : JpaRepository<User, Long> {
    @Query("select nextval('user_id_seq')", nativeQuery = true)
    fun getNextId(): Long

    fun findByUsername(username: String): User?
}