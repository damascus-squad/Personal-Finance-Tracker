package org.example.features.summary

import org.example.model.TransactionReport

interface Summary {
    fun getByMonth(year: Int, month: Int): TransactionReport
    fun getByYear(year: Int): TransactionReport
}