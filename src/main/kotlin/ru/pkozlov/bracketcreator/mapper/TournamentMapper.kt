package ru.pkozlov.bracketcreator.mapper

import ru.pkozlov.bracketcreator.domain.Tournament
import ru.pkozlov.bracketcreator.dto.TournamentDto

fun Tournament.mapToDto() = TournamentDto(
    id = id,
    name = name,
    date = date,
    place = place
)