package ru.pkozlov.bracketcreator.domain

import java.math.BigDecimal
import java.time.Year
import javax.persistence.*

@Entity
@Table(
    name = "category",
    indexes = [Index(
        name = "category_lower_birth_year_threshold_weight_idx",
        columnList = "lowerBirthYearThreshold, weight"
    )]
)
@SequenceGenerator(name = "category_id_seq_generator", sequenceName = "category_id_seq", allocationSize = 1)
class Category(
    @Id val id: Long,

    @Column(nullable = false)
    val lowerBirthYearThreshold: Year,

    @Column(nullable = false)
    val upperBirthYearThreshold: Year,

    @Column(nullable = false)
    val weight: BigDecimal
)