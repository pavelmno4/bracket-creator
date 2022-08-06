package ru.pkozlov.bracketcreator.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(
    name = "participant", indexes = [
        Index(name = "participant_team_id_idx", columnList = "team_id"),
        Index(name = "participant_category_id_idx", columnList = "category_id"),
        Index(name = "participant_tournament_id_idx", columnList = "tournament_id")
    ]
)
@SequenceGenerator(name = "participant_seq_generator", sequenceName = "participant_seq", allocationSize = 1)
class Participant(
    @Id val id: Long,

    val lastName: String,

    val firstName: String,

    val birthDate: LocalDate,

    val gender: Gender,

    val weight: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", foreignKey = ForeignKey(name = "participant_team_id_fk"))
    val team: Team,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "participant_category_id_fk"))
    val category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", foreignKey = ForeignKey(name = "participant_tournament_id_fk"))
    val tournament: Tournament
)

enum class Gender {
    MAN,
    WOMAN
}