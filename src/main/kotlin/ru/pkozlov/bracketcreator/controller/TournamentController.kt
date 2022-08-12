package ru.pkozlov.bracketcreator.controller

import org.springframework.web.bind.annotation.*
import ru.pkozlov.bracketcreator.dto.TournamentDto
import ru.pkozlov.bracketcreator.service.TournamentService

@RestController
class TournamentController(
    private val tournamentService: TournamentService
) {
    @GetMapping("/v1/tournament")
    fun findTournaments(): List<TournamentDto> = tournamentService.findAll()

    @GetMapping("/v1/tournament/{tournament-id}")
    fun findTournament(
        @PathVariable(name = "tournament-id") tournamentId: Long
    ): TournamentDto = tournamentService.findById(tournamentId)

    @PostMapping("/v1/tournament")
    fun createTournament(
        @RequestBody tournament: TournamentDto
    ): TournamentDto = tournamentService.create(tournament)

    @PutMapping("/v1/tournament/{tournament-id}")
    fun updateTournament(
        @PathVariable(name = "tournament-id") tournamentId: Long,
        @RequestBody tournament: TournamentDto
    ): TournamentDto = tournamentService.update(tournamentId, tournament)

    @DeleteMapping("/v1/tournament/{tournament-id}")
    fun deleteTournament(
        @PathVariable(name = "tournament-id") tournamentId: Long
    ): Unit = tournamentService.delete(tournamentId)
}