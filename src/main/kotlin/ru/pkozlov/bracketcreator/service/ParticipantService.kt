package ru.pkozlov.bracketcreator.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.pkozlov.bracketcreator.domain.Gender
import ru.pkozlov.bracketcreator.domain.Participant
import ru.pkozlov.bracketcreator.dto.AgeCategoryDto
import ru.pkozlov.bracketcreator.dto.ParticipantDto
import ru.pkozlov.bracketcreator.exceptions.NotFoundException
import ru.pkozlov.bracketcreator.mapper.mapToDto
import ru.pkozlov.bracketcreator.repository.ParticipantRepository
import java.math.BigDecimal

@Service
class ParticipantService(
    private val participantRepository: ParticipantRepository,
    private val tournamentService: TournamentService,
    private val teamService: TeamService,
    private val categoryService: CategoryService
) {
    fun find(
        tournamentId: Long,
        gender: Gender,
        ageCategoryDto: AgeCategoryDto,
        weightCategory: BigDecimal
    ): List<ParticipantDto> = categoryService
        .findByLowerBirthYearThresholdAndWeight(
            lowerBirthYearThreshold = ageCategoryDto.lowerBirthYearThreshold,
            weight = weightCategory
        )
        .let { category ->
            participantRepository.findAllByTournamentIdAndCategoryIdAndGender(
                tournamentId = tournamentId,
                categoryId = category.id,
                gender = gender
            )
        }
        .map(Participant::mapToDto)

    fun findWoman(tournamentId: Long): List<ParticipantDto> = participantRepository
        .findAllByTournamentIdAndGender(tournamentId, Gender.WOMAN)
        .map(Participant::mapToDto)

    @Transactional
    fun create(
        tournamentId: Long,
        participant: ParticipantDto
    ): ParticipantDto = Participant(
        id = participantRepository.getNextId(),
        lastName = participant.lastName,
        firstName = participant.firstName,
        birthDate = participant.birthDate,
        gender = participant.gender,
        team = teamService.findOrCreate(participant.team),
        category = categoryService.checkAndFind(
            yearOfBirth = participant.birthDate.year,
            category = participant.category
        ),
        tournament = tournamentService.findForInject(tournamentId)
    ).run(participantRepository::save).mapToDto()

    @Transactional
    fun update(
        participantId: Long,
        participant: ParticipantDto
    ): ParticipantDto = participantRepository
        .findByIdOrNull(participantId)
        ?.apply {
            lastName = participant.lastName
            firstName = participant.firstName
            birthDate = participant.birthDate
            gender = participant.gender
            weight = participant.weight ?: BigDecimal.ZERO
            team = teamService.findOrCreate(participant.team)
            category = categoryService.findByLowerBirthYearThresholdAndWeight(
                lowerBirthYearThreshold = participant.category.lowerBirthYearThreshold,
                weight = participant.category.weight
            )
        }
        ?.run(participantRepository::save)
        ?.mapToDto()
        ?: throw NotFoundException("Participant with id = $participantId not found")

    @Transactional
    fun delete(participantId: Long): Unit = participantRepository
        .findByIdOrNull(participantId)
        ?.run(participantRepository::delete)
        ?: throw NotFoundException("Participant with id = $participantId not found")
}