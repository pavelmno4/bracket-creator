package ru.pkozlov.bracketcreator.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.pkozlov.bracketcreator.domain.Category

interface CategoryRepository : JpaRepository<Category, Long> {
    @Query(value = "select nextval('category_id_seq')", nativeQuery = true)
    fun getNextId(): Long
}