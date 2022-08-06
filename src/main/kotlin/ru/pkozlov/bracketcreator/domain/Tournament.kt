package ru.pkozlov.bracketcreator.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
@SequenceGenerator(name = "tournament_seq_generator", sequenceName = "tournament_seq", allocationSize = 1)
class Tournament(
    @Id val id: Long,

    val name: String,

    val date: LocalDateTime,

    val place: String
)