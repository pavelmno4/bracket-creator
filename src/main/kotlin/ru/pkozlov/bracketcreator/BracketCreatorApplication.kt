package ru.pkozlov.bracketcreator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BracketCreatorApplication

fun main(args: Array<String>) {
    runApplication<BracketCreatorApplication>(*args)
}
