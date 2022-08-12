package ru.pkozlov.bracketcreator.dto

import java.math.BigDecimal

data class CategoryDto(
    val lowerBirthYearThreshold: Int,
    val upperBirthYearThreshold: Int,
    val weight: BigDecimal
)

data class CategoryView(
    val years: Set<Int>,
    val weights: Set<BigDecimal>
)
