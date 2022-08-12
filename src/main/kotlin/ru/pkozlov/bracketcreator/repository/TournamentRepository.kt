package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Tournament

interface TournamentRepository : JpaRepository<Tournament, Long> {
    @Query("select nextval('tournament_id_seq')", nativeQuery = true)
    fun getNextId(): Long
}