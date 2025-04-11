package org.example

import org.example.feature.TransactionMangerImp
import org.example.feature.runCLI

fun main() {
    println("Hello World!")
    val manger = TransactionMangerImp()
    runCLI(manger)
}