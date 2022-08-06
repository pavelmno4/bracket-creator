package ru.pkozlov.bracketcreator.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
@SequenceGenerator(name = "team_seq_generator", sequenceName = "team_seq", allocationSize = 1)
class Team(
    @Id val id: Long,

    val name: String,

    val city: String
)