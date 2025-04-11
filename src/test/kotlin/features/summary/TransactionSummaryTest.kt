package features.summary

import org.example.model.FakeCategory
import org.example.model.FakeTransaction
import org.example.model.FakeTransactionType
import org.example.model.TransactionReport
import org.example.features.summary.TransactionSummary
import org.example.model.CategorySummary
import java.time.LocalDate
import java.time.LocalDateTime


fun main() {
    val summary = TransactionSummary(emptyList())

    // Yearly report cases
    checkReport(
        name = "Should return empty report, When empty list is entered",
        result = TransactionSummary(emptyList()).getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf()
        )
    )

    checkReport(
        name = "Should succeed, When using income amount - Yearly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 50_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                )
            )
        ).getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            50_000.0,
            0.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 50_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )

    checkReport(
        name = "Should succeed, When using expense amount - Yearly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 25_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.RENT,
                ),
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 25_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.RENT,
                ),
            )
        ).getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            0.0,
            50_000.0,
            mutableMapOf(
                FakeCategory.RENT to CategorySummary(
                    category = FakeCategory.RENT,
                    amount = 50_000.0,
                    transactionsCount = 2,
                ),
            )
        )
    )

    checkReport(
        name = "Should succeed, When the report type is yearly - Yearly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 20_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                ),
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 40_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.FOOD,
                )
            )
        ).getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            20_000.0,
            40_000.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 20_000.0,
                    transactionsCount = 1,
                ),
                FakeCategory.FOOD to CategorySummary(
                    category = FakeCategory.FOOD,
                    amount = 40_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )

    checkReport(
        name = "Should fail, When the wrong category is reported - Yearly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 50_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                )
            )
        ).getByYear(
            year = 2025
        ),
        correctResult = TransactionReport(
            50_000.0,
            0.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 50_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )
//
//    // Monthly report cases
//
    checkReport(
        name = "Should return empty report, When empty list is entered - Monthly",
        result = TransactionSummary(emptyList()).getByMonth(2025, 5),
        correctResult = TransactionReport(
            0.0,
            0.0,
            mutableMapOf()
        )
    )

    checkReport(
        name = "Should succeed, When using income amount - Monthly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 50_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                )
            )
        ).getByMonth(2025, 5),
        correctResult = TransactionReport(
            50_000.0,
            0.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 50_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )

    checkReport(
        name = "Should succeed, When using expense amount - Monthly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 25_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.RENT,
                ),
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 25_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.RENT,
                ),
            )
        ).getByMonth(2025, 5),
        correctResult = TransactionReport(
            0.0,
            50_000.0,
            mutableMapOf(
                FakeCategory.RENT to CategorySummary(
                    category = FakeCategory.RENT,
                    amount = 50_000.0,
                    transactionsCount = 2,
                ),
            )
        )
    )

    checkReport(
        name = "Should succeed, When the report type is monthly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 20_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                ),
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 40_000.0,
                    transactionType = FakeTransactionType.EXPENSE,
                    category = FakeCategory.FOOD,
                )
            )
        ).getByMonth(2025, 5),
        correctResult = TransactionReport(
            20_000.0,
            40_000.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 20_000.0,
                    transactionsCount = 1,
                ),
                FakeCategory.FOOD to CategorySummary(
                    category = FakeCategory.FOOD,
                    amount = 40_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )

    checkReport(
        name = "Should fail, When the wrong category is reported - Monthly",
        result = TransactionSummary(
            listOf(
                FakeTransaction(
                    date = LocalDate.of(2025, 5, 21),
                    amount = 50_000.0,
                    transactionType = FakeTransactionType.INCOME,
                    category = FakeCategory.SALARY,
                )
            )
        ).getByMonth(2025, 5),
        correctResult = TransactionReport(
            50_000.0,
            0.0,
            mutableMapOf(
                FakeCategory.SALARY to CategorySummary(
                    category = FakeCategory.SALARY,
                    amount = 50_000.0,
                    transactionsCount = 1,
                )
            )
        )
    )
}

fun checkReport(
    name: String,
    result: TransactionReport,
    correctResult: TransactionReport,
) {
    if (result == correctResult) {
        println("\uD83D\uDFE9 Success - $name")
    } else {
        println("===============")
        println(result)
        println(correctResult)
        println("\uD83D\uDFE5 Failed - $name")
    }
}
