package ru.pkozlov.bracketcreator.exceptions

class NotFoundException(override val message: String) : RuntimeException(message)