package ru.pkozlov.bracketcreator.mapper

import ru.pkozlov.bracketcreator.domain.Participant
import ru.pkozlov.bracketcreator.dto.ParticipantDto

fun Participant.mapToDto() = ParticipantDto(
    id = id,
    lastName = lastName,
    firstName = firstName,
    birthDate = birthDate,
    gender = gender,
    category = category.mapToDto(),
    weight = weight,
    team = team.mapToDto()
)