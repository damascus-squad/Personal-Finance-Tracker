package test

import model.Transaction
import model.TransactionType
import org.example.features.summary.TransactionSummary
import org.example.model.Category
import org.example.model.CategorySummary
import org.example.model.TransactionReport
import util.CheckTest
import util.check
import java.time.LocalDateTime
import java.util.*

class TestSummary {

    @CheckTest
    fun testEmptyReport(): Boolean {
        return check(
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
    }

    @CheckTest
    fun testIncomeYearly(): Boolean {
        return check(
            name = "Should succeed, When using income amount - Yearly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 50_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2025
            ),
            correctResult = TransactionReport(
                50_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testExpenseYearly(): Boolean {
        return check(
            name = "Should succeed, When using expense amount - Yearly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 25_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(2, "Rent"),
                        description = "",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 25_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(2, "Rent"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2025
            ),
            correctResult = TransactionReport(
                0.0,
                50_000.0,
                mutableMapOf(
                    Category(2, "Rent") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 2
                    )
                )
            )
        )
    }

    @CheckTest
    fun testMixedTransactionsYearly(): Boolean {
        return check(
            name = "Should succeed, When the report type is yearly - Yearly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 20_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 40_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(3, "Food"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2025
            ),
            correctResult = TransactionReport(
                20_000.0,
                40_000.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 20_000.0,
                        transactionsCount = 1
                    ),
                    Category(3, "Food") to CategorySummary(
                        amount = 40_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testWrongCategoryYearly(): Boolean {
        return check(
            name = "Should fail, When the wrong category is reported - Yearly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 50_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2025
            ),
            correctResult = TransactionReport(
                50_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testEmptyReportMonthly(): Boolean {
        return check(
            name = "Should return empty report, When empty list is entered - Monthly",
            result = TransactionSummary(emptyList()).getByMonth(2025, 5),
            correctResult = TransactionReport(
                0.0,
                0.0,
                mutableMapOf()
            )
        )
    }

    @CheckTest
    fun testIncomeMonthly(): Boolean {
        return check(
            name = "Should succeed, When using income amount - Monthly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 50_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                50_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testExpenseMonthly(): Boolean {
        return check(
            name = "Should succeed, When using expense amount - Monthly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 25_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(2, "Rent"),
                        description = "",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 25_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(2, "Rent"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                0.0,
                50_000.0,
                mutableMapOf(
                    Category(2, "Rent") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 2
                    )
                )
            )
        )
    }

    @CheckTest
    fun testMixedTransactionsMonthly(): Boolean {
        return check(
            name = "Should succeed, When the report type is monthly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 20_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 40_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(3, "Food"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                20_000.0,
                40_000.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 20_000.0,
                        transactionsCount = 1
                    ),
                    Category(3, "Food") to CategorySummary(
                        amount = 40_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testWrongCategoryMonthly(): Boolean {
        return check(
            name = "Should fail, When the wrong category is reported - Monthly",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 50_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                50_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 50_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testTransactionFromDifferentYears(): Boolean {
        return check(
            name = "Should only include transactions from the requested year",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2024, 12, 31, 23, 59, 59),
                        amount = 10_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Previous year",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                        amount = 20_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Target year",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2026, 1, 1, 0, 0, 0),
                        amount = 30_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Next year",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2025
            ),
            correctResult = TransactionReport(
                20_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 20_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testTransactionFromDifferentMonths(): Boolean {
        return check(
            name = "Should only include transactions from the requested month",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 31, 23, 59, 59),
                        amount = 15_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Target month",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 4, 30, 23, 59, 59),
                        amount = 15_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Previous month",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 6, 1, 0, 0, 0),
                        amount = 15_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "Next month",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                15_000.0,
                0.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 15_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testLargeSumTransactions(): Boolean {
        return check(
            name = "Should handle very large sum transactions",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 999_999_999.99,
                        transactionType = TransactionType.INCOME,
                        category = Category(4, "Investment"),
                        description = "Large investment",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 999_999_999.99,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(5, "Real Estate"),
                        description = "Property purchase",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                999_999_999.99,
                999_999_999.99,
                mutableMapOf(
                    Category(4, "Investment") to CategorySummary(
                        amount = 999_999_999.99,
                        transactionsCount = 1
                    ),
                    Category(5, "Real Estate") to CategorySummary(
                        amount = 999_999_999.99,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

    @CheckTest
    fun testInvalidMonthValues(): Boolean {
        return check(
            name = "Should return empty report, When invalid month entered",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                        amount = 10_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "January salary",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 13),
            correctResult = TransactionReport(
                0.0,
                0.0,
                mutableMapOf()
            )
        )
    }

    @CheckTest
    fun testExtremeYearValues(): Boolean {
        return check(
            name = "Should return empty report, When invalid year entered",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                        amount = 10_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "January salary",
                        id = UUID.randomUUID()
                    )
                )
            ).getByYear(
                year = 2030
            ),
            correctResult = TransactionReport(
                0.0,
                0.0,
                mutableMapOf()
            )
        )
    }

    @CheckTest
    fun testMultipleCategoriesReport(): Boolean {
        return check(
            name = "Should correctly summarize multiple categories",
            result = TransactionSummary(
                listOf(
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 5_000.0,
                        transactionType = TransactionType.EXPENSE,
                        category = Category(2, "Rent"),
                        description = "",
                        id = UUID.randomUUID()
                    ),
                    Transaction(
                        date = LocalDateTime.of(2025, 5, 21, 4, 0, 0),
                        amount = 12_000.0,
                        transactionType = TransactionType.INCOME,
                        category = Category(1, "Salary"),
                        description = "",
                        id = UUID.randomUUID()
                    )
                )
            ).getByMonth(2025, 5),
            correctResult = TransactionReport(
                12_000.0,
                5_000.0,
                mutableMapOf(
                    Category(1, "Salary") to CategorySummary(
                        amount = 12_000.0,
                        transactionsCount = 1
                    ),
                    Category(2, "Rent") to CategorySummary(
                        amount = 5_000.0,
                        transactionsCount = 1
                    )
                )
            )
        )
    }

}
