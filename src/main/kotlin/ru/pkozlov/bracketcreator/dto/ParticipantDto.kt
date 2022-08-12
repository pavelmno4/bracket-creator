package ru.pkozlov.bracketcreator.dto

import com.fasterxml.jackson.annotation.JsonInclude
import ru.pkozlov.bracketcreator.domain.Gender
import java.math.BigDecimal
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ParticipantDto(
    val id: Long?,
    val lastName: String,
    val firstName: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val category: CategoryDto,
    val weight: BigDecimal?,
    val team: TeamDto
)
