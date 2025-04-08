package summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.Report
import org.example.summary.Summary
import java.time.LocalDateTime


fun main() {
    val summary = Summary()

    // Yearly report cases
    checkReport(
        name = "Should return empty report, When empty list is entered",
        result = summary.getByYear(
            fakeTransactions = emptyList(),
            year = 2025
        ),
        correctResult = Report(
            0,
            0,
            emptyMap()
        )
    )

    checkReport(
        name = "Should succeed, true process - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 1, 8, 15, 30), 0,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            year = 2025
        ),
        correctResult = Report(
            0,
            0,
            mapOf(
                Pair(FakeCategory.SALARY, 0)
            )
        )
    )

    checkReport(
        name = "Should succeed, When the income amount is positive - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), 12_000,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            year = 2025
        ),
        correctResult = Report(
            expenses = 0,
            income = 12_000,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.SALARY, 12_000))
        )
    )

    checkReport(
        name = "Should succeed, When the expenses amount is negative - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), -200,
                    fakeCategory = FakeCategory.RENT
                ),
            ),
            year = 2025
        ),
        correctResult = Report(
            expenses = -200,
            income = 0,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.RENT, -200))
        )
    )

    checkReport(
        name = "Should succeed, When the report type is yearly - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), 12_000,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            year = 2025
        ),
        correctResult = Report(
            expenses = 0,
            income = 12_000,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.SALARY, 12_000))
        )
    )

    checkReport(
        name = "Should fail, When the wrong category is reported - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), -2_000,
                    fakeCategory = FakeCategory.RENT
                ),
            ),
            year = 2025
        ),
        correctResult = Report(
            expenses = -2_000,
            income = 0,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.FOOD, -2_000))
        )
    )

    // Monthly report cases

    checkReport(
        name = "Should succeed, true process - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 1, 8, 15, 30), 0,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            month = 1,
            year = 2025
        ),
        correctResult = Report(
            0,
            0,
            mapOf(
                Pair(FakeCategory.SALARY, 0)
            )
        )

    )

    checkReport(
        name = "Should succeed, When the income amount is positive - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), 12_000,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = Report(
            expenses = 0,
            income = 12_000,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.SALARY, 12_000))
        )
    )

    checkReport(
        name = "Should succeed, When the expenses amount is negative - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), -200,
                    fakeCategory = FakeCategory.RENT
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = Report(
            expenses = -200,
            income = 0,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.RENT, -200))
        )
    )

    checkReport(
        name = "Should succeed, When the report type is monthly - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), 12_000,
                    fakeCategory = FakeCategory.SALARY
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = Report(
            expenses = 0,
            income = 12_000,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.SALARY, 12_000))
        )
    )

    checkReport(
        name = "Should fail, When the wrong category is reported - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDateTime.of(2025, 4, 8, 15, 30), -2_000,
                    fakeCategory = FakeCategory.RENT
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = Report(
            expenses = -2_000,
            income = 0,
            fakeCategorySummaries = mapOf(Pair(FakeCategory.FOOD, -2_000))
        )
    )

}

fun checkReport(
    name: String,
    result: Report,
    correctResult: Report
) {
    if (result == correctResult) {
        println("\uD83D\uDFE9 Success - $name")
    } else {
        println("\uD83D\uDFE5 Failed - $name")
    }
}
