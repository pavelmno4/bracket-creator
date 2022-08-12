package ru.pkozlov.bracketcreator.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.pkozlov.bracketcreator.domain.Tournament
import ru.pkozlov.bracketcreator.dto.TournamentDto
import ru.pkozlov.bracketcreator.exceptions.NotFoundException
import ru.pkozlov.bracketcreator.mapper.mapToDto
import ru.pkozlov.bracketcreator.repository.TournamentRepository

@Service
class TournamentService(
    private val tournamentRepository: TournamentRepository
) {
    fun findById(tournamentId: Long): TournamentDto = tournamentRepository
        .findByIdOrNull(tournamentId)?.mapToDto()
        ?: throw NotFoundException("Tournament with id = $tournamentId not found")

    fun findAll(): List<TournamentDto> = tournamentRepository
        .findAll()
        .map(Tournament::mapToDto)

    fun findForInject(tournamentId: Long): Tournament = tournamentRepository
        .findByIdOrNull(tournamentId)
        ?: throw NotFoundException("Tournament with id = $tournamentId not found")

    @Transactional
    fun create(tournament: TournamentDto): TournamentDto = Tournament(
        id = tournamentRepository.getNextId(),
        name = tournament.name,
        date = tournament.date,
        place = tournament.place
    ).run(tournamentRepository::save).mapToDto()

    @Transactional
    fun update(tournamentId: Long, tournament: TournamentDto): TournamentDto = tournamentRepository
        .findByIdOrNull(tournamentId)
        ?.apply {
            name = tournament.name
            date = tournament.date
            place = tournament.place
        }
        ?.run(Tournament::mapToDto)
        ?: throw NotFoundException("Tournament with id = $tournamentId not found")

    @Transactional
    fun delete(tournamentId: Long): Unit = tournamentRepository
        .findByIdOrNull(tournamentId)
        ?.run(tournamentRepository::delete)
        ?: throw NotFoundException("Tournament with id = $tournamentId not found")
}