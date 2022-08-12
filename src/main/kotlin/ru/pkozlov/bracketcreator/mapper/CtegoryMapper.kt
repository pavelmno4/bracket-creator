package ru.pkozlov.bracketcreator.mapper

import ru.pkozlov.bracketcreator.domain.Category
import ru.pkozlov.bracketcreator.dto.CategoryDto

fun Category.mapToDto() = CategoryDto(
    lowerBirthYearThreshold = lowerBirthYearThreshold.value,
    upperBirthYearThreshold = upperBirthYearThreshold.value,
    weight = weight
)
