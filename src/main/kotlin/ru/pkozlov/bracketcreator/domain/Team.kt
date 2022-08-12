package ru.pkozlov.bracketcreator.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
@SequenceGenerator(name = "team_id_seq_generator", sequenceName = "team_id_seq", allocationSize = 1)
class Team(
    @Id val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val city: String
)