package ru.pkozlov.bracketcreator.dto

import java.time.LocalDate

data class TournamentDto(
    val id: Long,
    val name: String,
    val date: LocalDate,
    val place: String
)
