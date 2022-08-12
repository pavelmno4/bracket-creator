package ru.pkozlov.bracketcreator.service

import org.springframework.stereotype.Service
import ru.pkozlov.bracketcreator.domain.Category
import ru.pkozlov.bracketcreator.dto.CategoryDto
import ru.pkozlov.bracketcreator.dto.CategoryView
import ru.pkozlov.bracketcreator.exceptions.BusinessException
import ru.pkozlov.bracketcreator.exceptions.NotFoundException
import ru.pkozlov.bracketcreator.repository.CategoryRepository
import java.math.BigDecimal
import java.time.Year

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun findAll(): List<CategoryView> = categoryRepository
        .findAll()
        .groupBy { it.lowerBirthYearThreshold to it.upperBirthYearThreshold }
        .run(::transformToView)

    fun checkAndFind(yearOfBirth: Int, category: CategoryDto): Category = category
        .run { if (yearOfBirth < lowerBirthYearThreshold) throw BusinessException("Выбрана неверная категория") else this }
        .run {
            categoryRepository.findByLowerBirthYearThresholdAndWeight(
                lowerBirthYearThreshold = Year.of(lowerBirthYearThreshold),
                weight = weight
            )
        }
        ?: throw NotFoundException("Category with lower birth year ${category.lowerBirthYearThreshold} and weight ${category.weight} not found")

    fun findByLowerBirthYearThresholdAndWeight(lowerBirthYearThreshold: Int, weight: BigDecimal): Category =
        categoryRepository
            .findByLowerBirthYearThresholdAndWeight(
                lowerBirthYearThreshold = Year.of(lowerBirthYearThreshold),
                weight = weight
            )
            ?: throw NotFoundException("Category with lower birth year $lowerBirthYearThreshold and weight $weight not found")

    private fun transformToView(categories: Map<Pair<Year, Year>, List<Category>>): List<CategoryView> = categories
        .map { (years, categories) ->
            CategoryView(
                years = setOf(years.first.value, years.second.value),
                weights = categories.map(Category::weight).toSet()
            )
        }
}