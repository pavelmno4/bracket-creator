package ru.pkozlov.bracketcreator.domain.converter

import java.time.Year
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class YearConverter : AttributeConverter<Year, Short> {
    override fun convertToDatabaseColumn(attribute: Year): Short = attribute.value.toShort()

    override fun convertToEntityAttribute(dbData: Short): Year = Year.of(dbData.toInt())
}