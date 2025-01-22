package com.example.theelephant.Model

class SpecialistProvider {
    fun getSpecialist(): List<Specialist> {
        return listOf(
            Specialist(
                name = "екатерина",
                surname = "колосова",
                phone = "111",
                password = "htwwye3",
                role = Specialist.Role.PSYCHOLOGIST,
                specialization = "Детский психолог"
            ),
            Specialist(
                name = "марина",
                surname = "иванова",
                phone = "79876543210",
                password = "speech2023",
                role = Specialist.Role.SPEECH_THERAPIST,
                specialization = "Коррекция речи"
            ),
            Specialist(
                name = "сергей",
                surname = "петров",
                phone = "79999999999",
                password = "neuro@123",
                role = Specialist.Role.NEUROPSYCHOLOGIST,
                specialization = "Нейропсихология"
            ),
            Specialist(
                name = "алена",
                surname = "сидорова",
                phone = "79881112233",
                password = "defect@456",
                role = Specialist.Role.ТEURODEFECTOLOGIST,
                specialization = "Дефектология"
            ),
            Specialist(
                name = "олег",
                surname = "григорьев",
                phone = "79888884444",
                password = "tomatis@789",
                role = Specialist.Role.TOMATIS,
                specialization = "Метод Томатис"
            ),
            Specialist(
                name = "алексей",
                surname = "кузнецов",
                phone = "79771234567",
                password = "massage@2023",
                role = Specialist.Role.MASSEUR,
                specialization = "Детский массаж"
            )
        )
    }
}