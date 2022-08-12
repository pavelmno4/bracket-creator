package ru.pkozlov.bracketcreator.service

import org.springframework.stereotype.Service
import ru.pkozlov.bracketcreator.domain.Team
import ru.pkozlov.bracketcreator.dto.TeamDto
import ru.pkozlov.bracketcreator.repository.TeamRepository

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {
    fun findOrCreate(team: TeamDto): Team = teamRepository
        .findByNameAndCity(team.name, team.city)
        ?: Team(
            id = teamRepository.getNextId(),
            name = team.name,
            city = team.city
        ).run(teamRepository::save)
}