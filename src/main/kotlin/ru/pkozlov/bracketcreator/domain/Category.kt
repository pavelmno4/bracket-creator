package ru.pkozlov.bracketcreator.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
@SequenceGenerator(name = "category_seq_generator", sequenceName = "category_seq", allocationSize = 1)
class Category(
    @Id val id: Long,

    val lowerBirthDateThreshold: LocalDate,

    val upperBirthDateThreshold: LocalDate,

    val weight: BigDecimal
)