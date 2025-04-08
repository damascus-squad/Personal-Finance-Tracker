package org.example.model
import java.time.LocalDateTime

class FakeTransaction (
    val date: LocalDateTime,
    val amount: Int,
    val fakeCategory: FakeCategory
) {

}
