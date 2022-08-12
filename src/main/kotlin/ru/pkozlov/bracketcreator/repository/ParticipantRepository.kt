package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Gender
import ru.pkozlov.bracketcreator.domain.Participant

interface ParticipantRepository : JpaRepository<Participant, Long> {
    @Query("select nextval('participant_id_seq')", nativeQuery = true)
    fun getNextId(): Long

    fun findAllByTournamentIdAndGender(tournamentId: Long, gender: Gender): List<Participant>

    fun findAllByTournamentIdAndCategoryIdAndGender(
        tournamentId: Long,
        categoryId: Long,
        gender: Gender
    ): List<Participant>
}