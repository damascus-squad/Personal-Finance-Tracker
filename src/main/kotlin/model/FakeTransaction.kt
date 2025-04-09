package org.example.model
import java.time.LocalDate

class FakeTransaction (
    val date: LocalDate,
    val amount: Double,
    val fakeCategory: FakeCategory
) {

}
