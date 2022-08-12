package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Team

interface TeamRepository : JpaRepository<Team, Long> {
    @Query("select nextval('team_id_seq')", nativeQuery = true)
    fun getNextId(): Long

    fun findByNameAndCity(name: String, city: String): Team?
}