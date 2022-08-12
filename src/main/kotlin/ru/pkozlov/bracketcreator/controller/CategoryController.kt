package ru.pkozlov.bracketcreator.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.pkozlov.bracketcreator.dto.CategoryView
import ru.pkozlov.bracketcreator.service.CategoryService

@RestController
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping("/v1/category")
    fun findAll(): List<CategoryView> = categoryService.findAll()
}