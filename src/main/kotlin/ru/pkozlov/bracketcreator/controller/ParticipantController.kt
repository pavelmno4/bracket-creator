package ru.pkozlov.bracketcreator.controller

import org.springframework.web.bind.annotation.*
import ru.pkozlov.bracketcreator.domain.Gender
import ru.pkozlov.bracketcreator.dto.AgeCategoryDto
import ru.pkozlov.bracketcreator.dto.ParticipantDto
import ru.pkozlov.bracketcreator.service.ParticipantService
import java.math.BigDecimal

@RestController
class ParticipantController(
    private val participantService: ParticipantService
) {

    @GetMapping("/v1/tournament/{tournament-id}/participant")
    fun findParticipants(
        @PathVariable(name = "tournament-id") tournamentId: Long,
        @RequestParam gender: Gender,
        @RequestParam ageCategory: AgeCategoryDto,
        @RequestParam weightCategory: BigDecimal,
    ): List<ParticipantDto> = participantService.find(tournamentId, gender, ageCategory, weightCategory)

    @GetMapping("/v1/tournament/{tournament-id}/participant/woman")
    fun findWomanParticipants(
        @PathVariable(name = "tournament-id") tournamentId: Long,
    ): List<ParticipantDto> = participantService.findWoman(tournamentId)

    @PostMapping("/v1/tournament/{tournament-id}/participant")
    fun create(
        @PathVariable(name = "tournament-id") tournamentId: Long,
        @RequestBody participant: ParticipantDto
    ): ParticipantDto = participantService.create(tournamentId, participant)

    @PutMapping("/v1/tournament/{tournament-id}/participant/{participant-id}")
    fun update(
        @PathVariable(name = "tournament-id") tournamentId: Long,
        @PathVariable(name = "participant-id") participantId: Long,
        @RequestBody participant: ParticipantDto
    ): ParticipantDto = participantService.update(participantId, participant)

    @DeleteMapping("/v1/tournament/{tournament-id}/participant/{participant-id}")
    fun delete(
        @PathVariable(name = "tournament-id") tournamentId: Long,
        @PathVariable(name = "participant-id") participantId: Long
    ): Unit = participantService.delete(participantId)
}