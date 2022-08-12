package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Category
import java.math.BigDecimal
import java.time.Year

interface CategoryRepository : JpaRepository<Category, Long> {
    @Query("select nextval('category_id_seq')", nativeQuery = true)
    fun getNextId(): Long

    fun findByLowerBirthYearThresholdAndWeight(lowerBirthYearThreshold: Year, weight: BigDecimal): Category?
}