package ru.pkozlov.bracketcreator.exceptions

class BusinessException(override val message: String) : RuntimeException(message)