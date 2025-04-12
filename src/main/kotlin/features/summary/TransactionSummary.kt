package org.example.features.summary

import org.example.model.*

class TransactionSummary(
    private val transactions: List<Transaction>
): Summary {

    private fun getReport(year: Int, month: Int? = null): TransactionReport {
        val transactionReport = TransactionReport(0.0, 0.0, mutableMapOf())

        val transactionFilter: (Transaction) -> Boolean = { transaction ->
            if (month == null) {
                transaction.date.year == year
            } else {
                transaction.date.year == year && transaction.date.month.value == month
            }
        }

        transactions.filter(transactionFilter).forEach { transaction ->
            when(transaction.transactionType) {
                TransactionType.INCOME -> transactionReport.income += transaction.amount
                TransactionType.EXPENSE -> transactionReport.expenses += transaction.amount
            }

            val categorySummary = transactionReport.categorySummaries.getOrDefault(
                key = transaction.category,
                defaultValue = CategorySummary()
            )

            categorySummary.transactionsCount++
            categorySummary.amount += transaction.amount

            transactionReport.categorySummaries[transaction.category] = categorySummary
        }

        return transactionReport
    }

    override fun getByMonth(year: Int, month: Int): TransactionReport {
        return getReport(year, month)
    }

    override fun getByYear(year: Int): TransactionReport {
        return getReport(year)
    }

}