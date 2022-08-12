package ru.pkozlov.bracketcreator.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
@SequenceGenerator(name = "tournament_id_seq_generator", sequenceName = "tournament_id_seq", allocationSize = 1)
class Tournament(
    @Id val id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var date: LocalDate,

    @Column(nullable = false)
    var place: String
)