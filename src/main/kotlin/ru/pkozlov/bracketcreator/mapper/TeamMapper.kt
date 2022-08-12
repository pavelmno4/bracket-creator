package ru.pkozlov.bracketcreator.mapper

import ru.pkozlov.bracketcreator.domain.Team
import ru.pkozlov.bracketcreator.dto.TeamDto

fun Team.mapToDto() = TeamDto(
    name = name,
    city = city
)