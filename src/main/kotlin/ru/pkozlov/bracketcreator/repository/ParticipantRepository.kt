package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Participant

interface ParticipantRepository : JpaRepository<Participant, Long> {
    @Query(value = "select nextval('participant_id_seq')", nativeQuery = true)
    fun getNextId(): Long
}