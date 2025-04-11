package features.summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.FakeTransactionType
import org.example.model.TransactionReport
import org.example.features.summary.TransactionSummary
import java.time.LocalDate


fun main() {
    val summary = TransactionSummary(emptyList())

    // Yearly report cases
    checkReport(
        name = "Should return empty report, When empty list is entered",
        result = summary.getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf()
        )
    )

    checkReport(
        name = "Should succeed, true process - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 1, 8), 0.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            year = 2025
        ),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf(
                Pair(FakeCategory.SALARY, 0.0)
            )
        )
    )

    checkReport(
        name = "Should succeed, When the income amount is positive - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 12_000.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            year = 2025
        ),
        correctResult = TransactionReport(
            expenses = 0.0,
            income = 12_000.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.SALARY, 12_000.0))
        )
    )

    checkReport(
        name = "Should succeed, When the expenses amount is negative - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 200.0,
                    category = FakeCategory.RENT,
                    transactionType = FakeTransactionType.EXPENSE
                ),
            ),
            year = 2025
        ),
        correctResult = TransactionReport(
            expenses = 200.0,
            income = 0.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.RENT, 200.0))
        )
    )

    checkReport(
        name = "Should succeed, When the report type is yearly - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 12_000.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            year = 2025
        ),
        correctResult = TransactionReport(
            expenses = 0.0,
            income = 12_000.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.SALARY, 12_000.0))
        )
    )

    checkReport(
        name = "Should fail, When the wrong category is reported - Yearly",
        result = summary.getByYear(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 2_000.0,
                    category = FakeCategory.FOOD,
                    transactionType = FakeTransactionType.EXPENSE
                ),
            ),
            year = 2025
        ),
        correctResult = TransactionReport(
            expenses = 2_000.0,
            income = 0.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.FOOD, 2_000.0))
        )
    )

    // Monthly report cases

    checkReport(
        name = "Should succeed, true process - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 1, 8), 0.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            month = 1,
            year = 2025
        ),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf(
                Pair(FakeCategory.SALARY, 0.0)
            )
        )

    )

    checkReport(
        name = "Should succeed, When the income amount is positive - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 12_000.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = TransactionReport(
            expenses = 0.0,
            income = 12_000.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.SALARY, 12_000.0))
        )
    )

    checkReport(
        name = "Should succeed, When the expenses amount is negative - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 200.0,
                    category = FakeCategory.RENT,
                    transactionType = FakeTransactionType.EXPENSE
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = TransactionReport(
            expenses = 200.0,
            income = 0.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.RENT, 200.0))
        )
    )

    checkReport(
        name = "Should succeed, When the report type is monthly - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 12_000.0,
                    category = FakeCategory.SALARY,
                    transactionType = FakeTransactionType.INCOME
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = TransactionReport(
            expenses = 0.0,
            income = 12_000.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.SALARY, 12_000.0))
        )
    )

    checkReport(
        name = "Should succeed, When the correct category is reported - Monthly",
        result = summary.getByMonth(
            fakeTransactions = listOf(
                FakeTransaction(
                    LocalDate.of(2025, 4, 8), 2_000.0,
                    category = FakeCategory.RENT,
                    transactionType = FakeTransactionType.EXPENSE
                ),
            ),
            year = 2025,
            month = 4
        ),
        correctResult = TransactionReport(
            expenses = 2_000.0,
            income = 0.0,
            fakeCategorySummaries = mutableMapOf(Pair(FakeCategory.RENT, 2_000.0))
        )
    )

    checkReport(
        name = "Should return empty report, When empty list is entered - Monthly",
        result = summary.getByMonth(
            fakeTransactions = emptyList(),
            year = 2025,
            month = 5
        ),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf()
        )
    )

}

fun checkReport(
    name: String,
    result: TransactionReport,
    correctResult: TransactionReport
) {
    if (result == correctResult) {
        println("\uD83D\uDFE9 Success - $name")
    } else {
        println("\uD83D\uDFE5 Failed - $name")
    }
}
