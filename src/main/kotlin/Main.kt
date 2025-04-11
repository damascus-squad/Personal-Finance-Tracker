package org.example

import org.example.feature.TransactionMangerImp
import org.example.feature.runCLI

fun main() {
    val manger = TransactionMangerImp()
    runCLI(manger)
}